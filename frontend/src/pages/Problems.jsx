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

    // 🔥 If no token → redirect immediately
    if (!token) {
      navigate("/");
      return;
    }

    try {
      const res = await fetch("http://localhost:8080/api/problems", {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`
        }
      });

      // 🔥 Handle expired/invalid token
      if (res.status === 401) {
        alert("Session expired. Please login again.");
        localStorage.removeItem("token");
        navigate("/");
        return;
      }

      const data = await res.json();
      setProblems(data);

    } catch (err) {
      console.error(err);
      alert("Error fetching problems");
    }
  };

  return (
    <div>
      <h1>Problems</h1>

      <button
        onClick={() => {
          localStorage.removeItem("token");
          navigate("/");
        }}
      >
        Logout
      </button>

      {problems.length === 0 ? (
        <p>No problems available</p>
      ) : (
        problems.map((p) => (
          <div key={p.id} style={{ margin: "10px 0" }}>
            <b>{p.title}</b> - {p.difficulty}

            <button onClick={() => navigate(`/problem/${p.id}`)}>
              Solve
            </button>
          </div>
        ))
      )}
    </div>
  );
}

export default Problems;