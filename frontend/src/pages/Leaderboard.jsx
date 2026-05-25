import { useEffect, useState } from "react";

function Leaderboard() {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetchLeaderboard();
  }, []);

  const fetchLeaderboard = async () => {
    const token = localStorage.getItem("token");

    const res = await fetch("http://localhost:8080/api/submissions/leaderboard", {
      headers: { Authorization: `Bearer ${token}` }
    });

    const d = await res.json();
    setData(d);
  };

  return (
    <div style={styles.container}>
      <h2>🏆 Leaderboard</h2>

      <table style={styles.table}>
            <thead>
                <tr>
                <th style={styles.th}>Rank</th>
                <th style={styles.th}>User</th>
                <th style={styles.th}>Score</th>
                <th style={styles.th}>Solved</th>
                <th style={styles.th}>Easy</th>
                <th style={styles.th}>Medium</th>
                <th style={styles.th}>Hard</th>
                </tr>
            </thead>

            <tbody>
                {data.map((u, i) => (
                <tr key={i} style={styles.row}>
                    <td style={styles.td}>{i + 1}</td>
                    <td style={styles.td}>{u.name}</td>
                    <td style={styles.td}>{u.score}</td>
                    <td style={styles.td}>{u.solved}</td>

                    <td style={{ ...styles.td, color: "#22c55e" }}>
                    {u.easy}
                    </td>

                    <td style={{ ...styles.td, color: "#facc15" }}>
                    {u.medium}
                    </td>

                    <td style={{ ...styles.td, color: "#ef4444" }}>
                    {u.hard}
                    </td>
                </tr>
                ))}
            </tbody>
        </table>
    </div>
  );
}

export default Leaderboard;

const styles = {
  container: {
    minHeight: "100vh",
    background: "#020617",
    color: "#fff",
    padding: "30px"
  },

  table: {
    width: "100%",
    borderCollapse: "collapse",
    marginTop: "20px"
  },

  th: {
    padding: "12px",
    textAlign: "left",
    borderBottom: "2px solid #334155",
    color: "#94a3b8"
  },

  td: {
    padding: "12px",
    borderBottom: "1px solid #1e293b"
  },

  row: {
    transition: "0.2s"
  }
};