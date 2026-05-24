import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Editor from "@monaco-editor/react";

function ProblemPage() {
  const { id } = useParams();

  const [problem, setProblem] = useState(null);

  // ✅ Default template
  const [code, setCode] = useState(`class Main {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        System.out.println(a + b);
    }
}`);

  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);

  // 🔥 Run feature
  const [customInput, setCustomInput] = useState("");
  const [runOutput, setRunOutput] = useState("");
  const [runLoading, setRunLoading] = useState(false);

  useEffect(() => {
    fetchProblem();
  }, []);

  const fetchProblem = async () => {
    const token = localStorage.getItem("token");

    try {
      const res = await fetch(`http://localhost:8080/api/problems/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });

      const data = await res.json();
      setProblem(data);

    } catch (err) {
      console.error(err);
    }
  };

  // 🔥 SUBMIT
  const handleSubmit = async () => {
    const token = localStorage.getItem("token");

    setLoading(true);
    setResult(null);

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

      if (!res.ok) {
        alert(data.message || "Submission failed");
        setLoading(false);
        return;
      }

      setResult(data);

    } catch (err) {
      console.error(err);
      alert("Submission failed");
    }

    setLoading(false);
  };

  // 🔥 RUN
  const handleRun = async () => {
    const token = localStorage.getItem("token");

    setRunLoading(true);
    setRunOutput("");

    try {
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

    } catch (err) {
      console.error(err);
      alert("Run failed");
    }

    setRunLoading(false);
  };

  if (!problem) return <p>Loading...</p>;

  return (
    <div style={{ padding: "20px" }}>
      <h1>{problem.title}</h1>

      <p><b>Difficulty:</b> {problem.difficulty}</p>
      <p>{problem.description}</p>

      <h3>Code Editor</h3>

      {/* 🔥 MONACO EDITOR */}
      <Editor
        height="400px"
        defaultLanguage="java"
        theme="vs-dark"
        value={code}
        onChange={(value) => setCode(value || "")}
      />

      <br />

      {/* 🔥 CUSTOM INPUT */}
      <h3>Custom Input</h3>
      <textarea
        rows="4"
        cols="50"
        value={customInput}
        onChange={(e) => setCustomInput(e.target.value)}
        placeholder="Example: 2 7"
      />

      <br /><br />

      {/* 🔥 BUTTONS */}
      <button onClick={handleRun} disabled={runLoading}>
        {runLoading ? "Running..." : "Run"}
      </button>

      <button
        onClick={handleSubmit}
        disabled={loading}
        style={{ marginLeft: "10px" }}
      >
        {loading ? "Compiling & Running..." : "Submit"}
      </button>

      {/* 🔥 RUN OUTPUT */}
      {runOutput && (
        <div style={{ marginTop: "15px", border: "1px solid gray", padding: "10px" }}>
          <b>Output:</b>
          <pre>{runOutput}</pre>
        </div>
      )}

      {/* 🔥 RESULT */}
      {result && (
        <div style={{ marginTop: "20px", border: "1px solid black", padding: "10px" }}>
          <h3>Submission Result</h3>

          <p>
            <b>Status:</b>{" "}
            <span style={{
              color:
                result.status === "ACCEPTED"
                  ? "green"
                  : result.status === "WRONG_ANSWER"
                  ? "red"
                  : "orange"
            }}>
              {result.status}
            </span>
          </p>

          <p>
            <b>Passed:</b> {result.passedTestCases} / {result.totalTestCases}
          </p>

          {result.errorType && (
            <p><b>Error:</b> {result.errorType}</p>
          )}
        </div>
      )}
    </div>
  );
}

export default ProblemPage;