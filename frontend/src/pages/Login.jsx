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

      console.log("Login response:", data);

      // 🔥 IMPORTANT FIX
      if (!data.token) {
        alert(data.message); // shows "Invalid password" or "User not found"
        return;
      }

      // ✅ store token ONLY if valid
      localStorage.setItem("token", data.token);

      alert("Login successful");

      navigate("/problems");

    } catch (err) {
      console.error(err);
      alert("Server error");
    }
  };

  return (
    <div>
      <h1>Login</h1>

      <input
        placeholder="Email"
        onChange={(e) => setEmail(e.target.value)}
      />

      <br /><br />

      <input
        type="password"
        placeholder="Password"
        onChange={(e) => setPassword(e.target.value)}
      />

      <br /><br />

      <button onClick={handleLogin}>Login</button>

      <p onClick={() => navigate("/register")}>
        Go to Register
      </p>
    </div>
  );
}

export default Login;