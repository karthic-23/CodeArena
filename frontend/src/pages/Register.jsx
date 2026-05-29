import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { apiFetch } from "../services/api";

function Register() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  const handleRegister = async () => {
    if (!name || !email || !password) {
      setError("All fields are required");
      return;
    }

    setLoading(true);
    setError("");
    setSuccess("");

    try {
      const res = await apiFetch("/api/users/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ name, email, password })
      });

      const data = await res.json();

      if (!res.ok) {
        setError(data.message || "Registration failed");
        setLoading(false);
        return;
      }

      setSuccess("Registration successful");

      setTimeout(() => navigate("/"), 1500);

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
        <p style={styles.subtitle}>Create your account</p>

        {/* 🔥 ERROR + SUCCESS */}
        {error && <div style={styles.errorBox}>{error}</div>}
        {success && <div style={styles.successBox}>{success}</div>}

        <input
          type="text"
          placeholder="Name"
          style={styles.input}
          onChange={(e) => setName(e.target.value)}
        />

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

        <button style={styles.button} onClick={handleRegister}>
          {loading ? "Registering..." : "Register"}
        </button>

        <p style={styles.link} onClick={() => navigate("/")}>
          Already have an account? <span style={{ color: "#8b5cf6" }}>Login</span>
        </p>
      </div>
    </div>
  );
}

export default Register;

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
  },

  successBox: {
    padding: "10px",
    borderRadius: "8px",
    background: "rgba(34,197,94,0.15)",
    border: "1px solid #22c55e",
    color: "#86efac",
    fontSize: "13px",
    textAlign: "center"
  }
};