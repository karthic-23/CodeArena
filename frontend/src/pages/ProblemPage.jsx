import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Editor from "@monaco-editor/react";

function ProblemPage() {
  const { id } = useParams();

  const [problem, setProblem] = useState(null);
  const [code, setCode] = useState(`class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}`);

  const [runOutput, setRunOutput] = useState([]);
  const [activeTest, setActiveTest] = useState(0);

  const [loading, setLoading] = useState(false);
  const [runLoading, setRunLoading] = useState(false);

  const [submissionResult, setSubmissionResult] = useState(null);

  useEffect(() => {
    fetchProblem();
  }, []);

  const fetchProblem = async () => {
    const token = localStorage.getItem("token");

    const res = await fetch(`http://localhost:8080/api/problems/${id}`, {
      headers: { Authorization: `Bearer ${token}` }
    });

    const data = await res.json();
    setProblem(data);
  };

  const getInputs = () => {
    if (!problem?.inputExample) return [];
    return problem.inputExample.split(";");
  };

  const getOutputs = () => {
    if (!problem?.outputExample) return [];
    return problem.outputExample.split(";");
  };

  // 🔥 RUN
  const handleRun = async () => {
    const token = localStorage.getItem("token");

    setRunLoading(true);
    setRunOutput([]);

    try {
      const inputs = getInputs();
      let outputs = [];

      for (let input of inputs) {
        const res = await fetch(
          `http://localhost:8080/api/run?language=JAVA&input=${encodeURIComponent(input)}`,
          {
            method: "POST",
            headers: {
              "Content-Type": "text/plain",
              Authorization: `Bearer ${token}`
            },
            body: code
          }
        );

        const data = await res.text();
        outputs.push(data.trim());
      }

      setRunOutput(outputs);

    } catch (err) {
      console.error(err);
      alert("Run failed");
    }

    setRunLoading(false);
  };

  // 🔥 SUBMIT
  const handleSubmit = async () => {
    const token = localStorage.getItem("token");

    setLoading(true);
    setSubmissionResult(null);

    try {
      const res = await fetch(
        `http://localhost:8080/api/submissions?problemId=${id}&language=JAVA`,
        {
          method: "POST",
          headers: {
            "Content-Type": "text/plain",
            Authorization: `Bearer ${token}`
          },
          body: code
        }
      );

      const data = await res.json();
      setSubmissionResult(data);

    } catch (err) {
      console.error(err);
      alert("Submission failed");
    }

    setLoading(false);
  };

  if (!problem) return <p style={{ color: "white" }}>Loading...</p>;

  const inputs = getInputs();
  const outputs = getOutputs();

  return (
    <div style={styles.container}>

      {/* LEFT PANEL */}
      <div style={styles.left}>
        <h2>{problem.title}</h2>
        <p style={styles.diff}>Difficulty: {problem.difficulty}</p>
        <p>{problem.description}</p>

        <h3 style={{ marginTop: "20px" }}>Examples</h3>

        {/* TESTCASE TABS */}
        <div style={styles.tabRow}>
          {inputs.map((_, i) => (
            <button
              key={i}
              style={{
                ...styles.tabBtn,
                background: activeTest === i ? "#6366f1" : "#1e293b"
              }}
              onClick={() => setActiveTest(i)}
            >
              Case {i + 1}
            </button>
          ))}
        </div>

        {/* ACTIVE TEST */}
        <div style={styles.exampleBox}>
          <p><b>Input:</b></p>
          <pre>{inputs[activeTest]}</pre>

          <p><b>Output:</b></p>
          <pre>{outputs[activeTest]}</pre>
        </div>

        {/* RUN RESULT */}
        {runOutput.length > 0 && (
          <div style={styles.exampleBox}>
            <p><b>Your Output:</b></p>
            <pre>{runOutput[activeTest]}</pre>

            <p style={{
              color:
                runOutput[activeTest] === outputs[activeTest]
                  ? "#22c55e"
                  : "#ef4444"
            }}>
              {runOutput[activeTest] === outputs[activeTest]
                ? "✔ Passed"
                : "✖ Failed"}
            </p>
          </div>
        )}

        {/* 🔥 SUBMISSION RESULT */}
        {submissionResult && (
          <div style={styles.resultBox}>

            <h3 style={{
              color:
                submissionResult.status === "ACCEPTED"
                  ? "#22c55e"
                  : "#ef4444"
            }}>
              {submissionResult.status}
            </h3>

            <p>
              Passed: {submissionResult.passedTestCases} / {submissionResult.totalTestCases}
            </p>

            {submissionResult.status !== "ACCEPTED" && (
              <div style={{ marginTop: "10px" }}>
                <p><b>Failed Test Case:</b> #{submissionResult.failedTestCase}</p>

                {submissionResult.errorType && (
                  <p><b>Error:</b> {submissionResult.errorType}</p>
                )}
              </div>
            )}

          </div>
        )}
      </div>

      {/* RIGHT PANEL */}
      <div style={styles.right}>

        {/* BIG EDITOR */}
        <Editor
          height="80%"
          theme="vs-dark"
          defaultLanguage="java"
          value={code}
          onChange={(value) => setCode(value || "")}
        />

        {/* BUTTONS */}
        <div style={styles.btnRow}>
          <button style={styles.runBtn} onClick={handleRun}>
            {runLoading ? "Running..." : "Run"}
          </button>

          <button style={styles.submitBtn} onClick={handleSubmit}>
            {loading ? "Submitting..." : "Submit"}
          </button>
        </div>

      </div>
    </div>
  );
}

export default ProblemPage;

const styles = {
  container: {
    display: "flex",
    height: "100vh",
    background: "#020617",
    color: "#fff"
  },

  left: {
    width: "45%",
    padding: "25px",
    borderRight: "1px solid #1e293b",
    overflowY: "auto"
  },

  right: {
    width: "55%",
    display: "flex",
    flexDirection: "column",
    gap: "10px",
    padding: "10px"
  },

  diff: { color: "#facc15" },

  exampleBox: {
    background: "#0f172a",
    padding: "14px",
    borderRadius: "10px",
    marginTop: "12px",
    border: "1px solid #1e293b"
  },

  tabRow: {
    display: "flex",
    gap: "10px",
    marginTop: "10px"
  },

  tabBtn: {
    padding: "8px 14px",
    border: "none",
    borderRadius: "6px",
    color: "#fff",
    cursor: "pointer"
  },

  btnRow: {
    display: "flex",
    gap: "12px",
    marginTop: "10px"
  },

  runBtn: {
    flex: 1,
    padding: "12px",
    background: "#22c55e",
    borderRadius: "8px",
    border: "none",
    color: "#fff"
  },

  submitBtn: {
    flex: 1,
    padding: "12px",
    background: "#6366f1",
    borderRadius: "8px",
    border: "none",
    color: "#fff"
  },

  resultBox: {
    marginTop: "15px",
    padding: "15px",
    borderRadius: "10px",
    background: "#0f172a",
    border: "1px solid #1e293b"
  }
};