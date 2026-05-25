import { useEffect, useState } from "react";

function Profile() {
  const [stats, setStats] = useState(null);

  useEffect(() => {
    fetchStats();
  }, []);

  const fetchStats = async () => {
    try {
      const token = localStorage.getItem("token");

      const res = await fetch("http://localhost:8080/api/submissions/stats", {
        headers: { Authorization: `Bearer ${token}` }
      });

      const data = await res.json();
      setStats(data);

    } catch (err) {
      console.error("Stats fetch failed");
    }
  };

  if (!stats) return <p style={{ color: "white" }}>Loading...</p>;

  const percent = Math.round((stats.solved / stats.total) * 100);

  return (
    <div style={styles.container}>

      <h2 style={styles.title}>👤 Profile</h2>

      {/* 🔥 MAIN CARD */}
      <div style={styles.card}>
        <h3>Solved Problems</h3>

        <p style={styles.solvedText}>
          {stats.solved} / {stats.total}
        </p>

        {/* 🔥 PROGRESS BAR */}
        <div style={styles.bar}>
          <div
            style={{
              ...styles.fill,
              width: `${percent}%`
            }}
          />
        </div>

        <p style={styles.percent}>{percent}% completed</p>
      </div>

      {/* 🔥 DIFFICULTY CARDS */}
      <div style={styles.row}>
        <div style={{ ...styles.statCard, borderLeft: "5px solid #22c55e" }}>
          🟢 Easy
          <p>{stats.easy}</p>
        </div>

        <div style={{ ...styles.statCard, borderLeft: "5px solid #facc15" }}>
          🟡 Medium
          <p>{stats.medium}</p>
        </div>

        <div style={{ ...styles.statCard, borderLeft: "5px solid #ef4444" }}>
          🔴 Hard
          <p>{stats.hard}</p>
        </div>
      </div>

    </div>
  );
}

export default Profile;

const styles = {
  container: {
    minHeight: "100vh",
    background: "#020617",
    color: "#fff",
    padding: "30px",
    fontFamily: "Inter"
  },

  title: {
    marginBottom: "25px"
  },

  card: {
    background: "#1e293b",
    padding: "25px",
    borderRadius: "12px",
    marginBottom: "30px",
    width: "400px"
  },

  solvedText: {
    fontSize: "22px",
    margin: "10px 0"
  },

  bar: {
    height: "12px",
    background: "#0f172a",
    borderRadius: "10px",
    overflow: "hidden",
    marginTop: "10px"
  },

  fill: {
    height: "100%",
    background: "linear-gradient(90deg, #22c55e, #4ade80)",
    transition: "0.6s"
  },

  percent: {
    marginTop: "10px",
    fontSize: "14px",
    color: "#94a3b8"
  },

  row: {
    display: "flex",
    gap: "20px"
  },

  statCard: {
    background: "#1e293b",
    padding: "20px",
    borderRadius: "10px",
    width: "150px",
    fontSize: "16px"
  }
};