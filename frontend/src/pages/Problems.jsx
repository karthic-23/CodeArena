import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { apiFetch } from "../services/api";

function Problems() {
  const [problems, setProblems] = useState([]);
  const [search, setSearch] = useState("");
  const [solvedProblems, setSolvedProblems] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    fetchProblems();
    fetchSolved();

    // 🔥 REFRESH WHEN USER RETURNS TO PAGE
    window.addEventListener("focus", fetchSolved);

    return () => {
      window.removeEventListener("focus", fetchSolved);
    };
  }, []);

  // 🔹 FETCH ALL PROBLEMS
  const fetchProblems = async () => {
    const token = localStorage.getItem("token");

    try {
      const res = await apiFetch("/api/problems", {
        headers: { Authorization: `Bearer ${token}` }
      });

      const data = await res.json();
      setProblems(data);

    } catch (err) {
      console.error("Problems fetch failed");
    }
  };

  // 🔹 FETCH SOLVED PROBLEMS
  const fetchSolved = async () => {
    try {
      const token = localStorage.getItem("token");

      const res = await apiFetch("/api/submissions/solved", {
        headers: { Authorization: `Bearer ${token}` }
      });

      const data = await res.json();

      console.log("Solved API:", data);

      // 🔥 FIX TYPE HERE
      setSolvedProblems(data.map(Number));

    } catch (err) {
      console.error("Solved fetch failed");
    }
  };

  const logout = () => {
    localStorage.removeItem("token");
    navigate("/");
  };

  const filtered = problems.filter(p =>
    p.title.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div style={styles.container}>

      {/* NAVBAR */}
      <div style={styles.navbar}>
        <h2 style={styles.logo}>CodeArena</h2>

        <input
          placeholder="Search problems..."
          style={styles.search}
          onChange={(e) => setSearch(e.target.value)}
        />

        {/* 🔥 PROFILE BUTTON */}
        <button
          style={styles.profileBtn}
          onClick={() => navigate("/profile")}
        >
          Profile
        </button>

        <button
          style={styles.profileBtn}
          onClick={() => navigate("/leaderboard")}
        >
          Leaderboard
        </button>

        <button style={styles.logout} onClick={logout}>
          Logout
        </button>
      </div>

      {/* GRID */}
      <div style={styles.grid}>
        {filtered.map((p) => {

          // 🔥 FINAL SOLVED CHECK
          const isSolved = solvedProblems.includes(Number(p.id));

          return (
            <div
              key={p.id}
              style={styles.card}
              onClick={() => navigate(`/problem/${p.id}`)}
            >
              <h3>
                {isSolved && (
                  <span style={{ color: "#22c55e", marginRight: "8px" }}>
                    ✔
                  </span>
                )}
                {p.title}
              </h3>

              <span style={{
                ...styles.badge,
                background:
                  p.difficulty === "EASY"
                    ? "#22c55e"
                    : p.difficulty === "MEDIUM"
                    ? "#facc15"
                    : "#ef4444"
              }}>
                {p.difficulty}
              </span>
            </div>
          );
        })}
      </div>

    </div>
  );
}

export default Problems;

const styles = {
  container: {
    minHeight: "100vh",
    background: "#020617",
    color: "#fff",
    padding: "20px",
    fontFamily: "Inter"
  },

  navbar: {
    display: "flex",
    gap: "15px",
    alignItems: "center",
    marginBottom: "30px"
  },

  logo: { margin: 0 },

  search: {
    flex: 1,
    padding: "10px",
    borderRadius: "8px",
    border: "none",
    background: "#1e293b",
    color: "#fff"
  },

  logout: {
    padding: "10px 15px",
    background: "#ef4444",
    border: "none",
    borderRadius: "6px",
    color: "#fff",
    cursor: "pointer"
  },

  grid: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fit, minmax(250px, 1fr))",
    gap: "20px"
  },

  card: {
    padding: "20px",
    borderRadius: "12px",
    background: "#1e293b",
    cursor: "pointer",
    transition: "0.2s"
  },

  badge: {
    padding: "5px 10px",
    borderRadius: "20px",
    fontSize: "12px"
  },

  profileBtn: {
    padding: "10px 15px",
    background: "#3b82f6", // blue
    border: "none",
    borderRadius: "6px",
    color: "#fff",
    cursor: "pointer"
  }
};