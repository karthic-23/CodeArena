import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    if (!email || !password) {
      alert("Enter email and password");
      return;
    }

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
        alert(data.message);
        return;
      }

      localStorage.setItem("token", data.token);
      navigate("/problems");

    } catch (err) {
      console.error(err);
      alert("Server error");
    }
  };

  return (
    <div style={styles.container}>

      {/* 🔥 TAGLINE */}
      <div style={styles.tagline}>
        Practice. Compete. Become better.
      </div>

      {/* 🔥 CODE BACKGROUND */}
      <div style={styles.codeBg}>
{`function solve(a, b) {
  return a + b;
}

class Solution {
  public static void main(String[] args) {
    System.out.println("CodeArena");
  }
}

for(int i = 0; i < n; i++) {
  cout << i << endl;
}

def solve():
    return input()

while(true) {
    // keep coding
}
`.repeat(20)}
      </div>

      {/* 🔥 FLOATING DOTS */}
      <div style={styles.dots}></div>

      {/* 🔥 GLOW */}
      <div style={styles.glow1}></div>
      <div style={styles.glow2}></div>

      {/* 🔥 CARD */}
      <div style={styles.card}>
        <h1 style={styles.title}>CodeArena</h1>
        <p style={styles.subtitle}>Login to continue</p>

        <input
          type="email"
          placeholder="Email"
          style={styles.input}
          onFocus={(e) => e.target.style.border = "1px solid #8b5cf6"}
          onBlur={(e) => e.target.style.border = "1px solid rgba(255,255,255,0.15)"}
          onChange={(e) => setEmail(e.target.value)}
        />

        <input
          type="password"
          placeholder="Password"
          style={styles.input}
          onFocus={(e) => e.target.style.border = "1px solid #8b5cf6"}
          onBlur={(e) => e.target.style.border = "1px solid rgba(255,255,255,0.15)"}
          onChange={(e) => setPassword(e.target.value)}
        />

        <button
          style={styles.button}
          onClick={handleLogin}
          onMouseOver={(e) => (e.target.style.transform = "scale(1.03)")}
          onMouseOut={(e) => (e.target.style.transform = "scale(1)")}
        >
          Login
        </button>

        <p style={styles.link} onClick={() => navigate("/register")}>
          Don’t have an account? <span style={{ color: "#8b5cf6" }}>Register</span>
        </p>
      </div>

      {/* 🔥 FOOTER */}
      <div style={styles.footer}>
        Built with focus by Karthic
      </div>

    </div>
  );
}

export default Login;

const styles = {
  container: {
    height: "100vh",
    width: "100vw",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    background: "linear-gradient(135deg, #0f172a, #1e293b)",
    position: "relative",
    overflow: "hidden",
    fontFamily: "Inter, sans-serif",
    color: "#fff"
  },

  tagline: {
    position: "absolute",
    top: "40px",
    fontSize: "14px",
    color: "#888",
    letterSpacing: "1px"
  },

  codeBg: {
    position: "absolute",
    width: "200%",
    height: "200%",
    top: "-50%",
    left: "-50%",
    fontSize: "14px",
    lineHeight: "22px",
    color: "rgba(255,255,255,0.04)",
    whiteSpace: "pre",
    transform: "rotate(-15deg)",
    zIndex: 0
  },

  dots: {
    position: "absolute",
    width: "100%",
    height: "100%",
    backgroundImage: "radial-gradient(rgba(255,255,255,0.05) 1px, transparent 1px)",
    backgroundSize: "40px 40px",
    zIndex: 0
  },

  glow1: {
    position: "absolute",
    width: "500px",
    height: "500px",
    background: "radial-gradient(circle, rgba(99,102,241,0.25), transparent)",
    top: "-150px",
    left: "-150px",
    filter: "blur(120px)",
    zIndex: 1
  },

  glow2: {
    position: "absolute",
    width: "500px",
    height: "500px",
    background: "radial-gradient(circle, rgba(139,92,246,0.25), transparent)",
    bottom: "-150px",
    right: "-150px",
    filter: "blur(120px)",
    zIndex: 1
  },

  card: {
    width: "360px",
    padding: "35px",
    borderRadius: "16px",
    background: "rgba(255,255,255,0.05)",
    border: "1px solid rgba(255,255,255,0.08)",
    backdropFilter: "blur(12px)",
    boxShadow: "0 20px 60px rgba(0,0,0,0.6)",
    display: "flex",
    flexDirection: "column",
    gap: "18px",
    zIndex: 2
  },

  title: {
    textAlign: "center",
    margin: 0,
    fontSize: "28px"
  },

  subtitle: {
    textAlign: "center",
    fontSize: "13px",
    color: "#aaa"
  },

  input: {
    padding: "14px",
    borderRadius: "10px",
    border: "1px solid rgba(255,255,255,0.15)",
    background: "rgba(255,255,255,0.05)",
    color: "#fff",
    outline: "none",
    transition: "0.2s"
  },

  button: {
    padding: "14px",
    borderRadius: "10px",
    border: "none",
    background: "linear-gradient(135deg, #6366f1, #8b5cf6)",
    color: "#fff",
    fontWeight: "600",
    cursor: "pointer",
    transition: "0.2s"
  },

  link: {
    textAlign: "center",
    fontSize: "13px",
    cursor: "pointer",
    color: "#aaa"
  },

  footer: {
    position: "absolute",
    bottom: "20px",
    fontSize: "12px",
    color: "#777"
  }
};