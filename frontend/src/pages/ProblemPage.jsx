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
  const [customInput, setCustomInput] = useState("");
  const [runOutput, setRunOutput] = useState("");
  const [result, setResult] = useState(null);

  const [loading, setLoading] = useState(false);
  const [runLoading, setRunLoading] = useState(false);

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

  const handleRun = async () => {
    const token = localStorage.getItem("token");

    setRunLoading(true);
    setRunOutput("");

    const res = await fetch(
      `http://localhost:8080/api/run?language=JAVA&input=${encodeURIComponent(customInput)}`,
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
    setRunOutput(data);
    setRunLoading(false);
  };

  const handleSubmit = async () => {
    const token = localStorage.getItem("token");

    setLoading(true);
    setResult(null);

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
    setResult(data);
    setLoading(false);
  };

  if (!problem) return <p style={{ color: "white" }}>Loading...</p>;

  return (
    <div style={styles.container}>

      {/* LEFT SIDE */}
      <div style={styles.left}>
        <h2>{problem.title}</h2>

        <p style={styles.diff}>
          Difficulty: {problem.difficulty}
        </p>

        <p style={styles.desc}>{problem.description}</p>
      </div>

      {/* RIGHT SIDE */}
      <div style={styles.right}>

        {/* EDITOR */}
        <Editor
          height="50%"
          theme="vs-dark"
          defaultLanguage="java"
          value={code}
          onChange={(value) => setCode(value || "")}
        />

        {/* INPUT */}
        <textarea
          placeholder="Custom Input"
          style={styles.input}
          value={customInput}
          onChange={(e) => setCustomInput(e.target.value)}
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

        {/* OUTPUT */}
        {runOutput && (
          <div style={styles.output}>
            <b>Output:</b>
            <pre>{runOutput}</pre>
          </div>
        )}

        {/* RESULT */}
        {result && (
          <div style={styles.result}>
            <b>Status:</b> {result.status}
          </div>
        )}

      </div>
    </div>
  );
}

export default ProblemPage;

const styles = {
  container: {
    display: "flex",
    height: "100vh",
    background: "#0f172a",
    color: "#fff"
  },

  left: {
    width: "50%",
    padding: "20px",
    overflowY: "auto",
    borderRight: "1px solid #333"
  },

  right: {
    width: "50%",
    display: "flex",
    flexDirection: "column",
    padding: "10px",
    gap: "10px"
  },

  diff: {
    color: "#facc15"
  },

  desc: {
    marginTop: "10px",
    lineHeight: "1.5"
  },

  input: {
    padding: "10px",
    borderRadius: "6px",
    border: "none",
    background: "#1e293b",
    color: "#fff"
  },

  btnRow: {
    display: "flex",
    gap: "10px"
  },

  runBtn: {
    flex: 1,
    padding: "10px",
    background: "#22c55e",
    border: "none",
    borderRadius: "6px",
    color: "#fff",
    cursor: "pointer"
  },

  submitBtn: {
    flex: 1,
    padding: "10px",
    background: "#6366f1",
    border: "none",
    borderRadius: "6px",
    color: "#fff",
    cursor: "pointer"
  },

  output: {
    background: "#020617",
    padding: "10px",
    borderRadius: "6px"
  },

  result: {
    padding: "10px",
    background: "#111827",
    borderRadius: "6px"
  }
};