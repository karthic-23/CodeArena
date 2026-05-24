import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function Problems() {
  const [problems, setProblems] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchProblems();
  }, []);

  const fetchProblems = async () => {
    const token = localStorage.getItem("token");

    const res = await fetch("http://localhost:8080/api/problems", {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });

    const data = await res.json();
    setProblems(data);
  };

  const logout = () => {
    localStorage.removeItem("token");
    navigate("/");
  };

  return (
    <div style={styles.container}>

      {/* 🔥 NAVBAR */}
      <div style={styles.navbar}>
        <h2 style={styles.logo}>CodeArena</h2>

        <button style={styles.logout} onClick={logout}>
          Logout
        </button>
      </div>

      {/* 🔥 TITLE */}
      <h1 style={styles.title}>Problems</h1>

      {/* 🔥 PROBLEM LIST */}
      <div style={styles.grid}>
        {problems.map((p) => (
          <div key={p.id} style={styles.card}>

            <h3 style={styles.problemTitle}>{p.title}</h3>

            <p style={{
              color:
                p.difficulty === "EASY"
                  ? "#22c55e"
                  : p.difficulty === "MEDIUM"
                  ? "#facc15"
                  : "#ef4444"
            }}>
              {p.difficulty}
            </p>

            <button
              style={styles.solveBtn}
              onClick={() => navigate(`/problem/${p.id}`)}
            >
              Solve
            </button>

          </div>
        ))}
      </div>

    </div>
  );
}

export default Problems;

const styles = {
  container: {
    minHeight: "100vh",
    background: "linear-gradient(135deg, #0f172a, #1e293b)",
    color: "#fff",
    fontFamily: "Inter, sans-serif",
    padding: "20px"
  },

  navbar: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: "30px"
  },

  logo: {
    margin: 0
  },

  logout: {
    padding: "8px 16px",
    background: "#ef4444",
    border: "none",
    borderRadius: "6px",
    color: "#fff",
    cursor: "pointer"
  },

  title: {
    textAlign: "center",
    marginBottom: "30px"
  },

  grid: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fit, minmax(250px, 1fr))",
    gap: "20px"
  },

  card: {
    padding: "20px",
    borderRadius: "12px",
    background: "rgba(255,255,255,0.05)",
    border: "1px solid rgba(255,255,255,0.1)",
    backdropFilter: "blur(10px)",
    display: "flex",
    flexDirection: "column",
    gap: "10px",
    transition: "0.2s"
  },

  problemTitle: {
    margin: 0
  },

  solveBtn: {
    marginTop: "10px",
    padding: "10px",
    borderRadius: "8px",
    border: "none",
    background: "linear-gradient(135deg, #6366f1, #8b5cf6)",
    color: "#fff",
    cursor: "pointer"
  }
};