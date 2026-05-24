import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  const handleLogin = async () => {
    if (!email || !password) {
      setError("Enter email and password");
      return;
    }

    setLoading(true);
    setError("");

    try {
      const res = await fetch("http://localhost:8080/api/users/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
      });

      const data = await res.json();

      if (!data.token) {
        setError(data.message || "Invalid credentials");
        setLoading(false);
        return;
      }

      localStorage.setItem("token", data.token);
      navigate("/problems");

    } catch (err) {
      console.error(err);
      setError("Server error");
    }

    setLoading(false);
  };

  return (
    <div style={styles.container}>

      <div style={styles.card}>
        <h1 style={styles.title}>CodeArena</h1>
        <p style={styles.subtitle}>Login to continue</p>

        {/* 🔥 ERROR UI */}
        {error && <div style={styles.errorBox}>{error}</div>}

        <input
          type="email"
          placeholder="Email"
          style={styles.input}
          onChange={(e) => setEmail(e.target.value)}
        />

        <input
          type="password"
          placeholder="Password"
          style={styles.input}
          onChange={(e) => setPassword(e.target.value)}
        />

        <button style={styles.button} onClick={handleLogin}>
          {loading ? "Logging in..." : "Login"}
        </button>

        <p style={styles.link} onClick={() => navigate("/register")}>
          Don’t have an account? <span style={{ color: "#8b5cf6" }}>Register</span>
        </p>
      </div>
    </div>
  );
}

export default Login;

const styles = {
  container: {
    height: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    background: "linear-gradient(135deg, #0f172a, #1e293b)",
    color: "#fff"
  },

  card: {
    width: "360px",
    padding: "30px",
    borderRadius: "15px",
    backdropFilter: "blur(15px)",
    background: "rgba(255,255,255,0.05)",
    border: "1px solid rgba(255,255,255,0.08)",
    display: "flex",
    flexDirection: "column",
    gap: "15px"
  },

  title: { textAlign: "center" },
  subtitle: { textAlign: "center", color: "#aaa" },

  input: {
    padding: "12px",
    borderRadius: "8px",
    border: "1px solid rgba(255,255,255,0.15)",
    background: "rgba(255,255,255,0.05)",
    color: "#fff"
  },

  button: {
    padding: "12px",
    borderRadius: "8px",
    background: "#6366f1",
    border: "none",
    color: "#fff",
    cursor: "pointer"
  },

  link: {
    textAlign: "center",
    cursor: "pointer",
    color: "#aaa"
  },

  errorBox: {
    padding: "10px",
    borderRadius: "8px",
    background: "rgba(239,68,68,0.15)",
    border: "1px solid #ef4444",
    color: "#fca5a5",
    fontSize: "13px",
    textAlign: "center"
  }
};