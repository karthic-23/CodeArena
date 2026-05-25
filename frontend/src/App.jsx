import { Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Problems from "./pages/Problems";
import ProblemPage from "./pages/ProblemPage";
import Profile from "./pages/Profile";
import Leaderboard from "./pages/Leaderboard";
import ProtectedRoute from "./components/ProtectedRoute";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/register" element={<Register />} />

      {/* 🔐 Protected routes */}
      <Route
        path="/problems"
        element={
          <ProtectedRoute>
            <Problems />
          </ProtectedRoute>
        }
      />

      <Route
        path="/problem/:id"
        element={
          <ProtectedRoute>
            <ProblemPage />
          </ProtectedRoute>
        }
      />

      <Route
        path="/profile"
        element={
          <ProtectedRoute>
            <Profile />
          </ProtectedRoute>
        }
      />

      <Route
        path="/leaderboard"
        element={
          <ProtectedRoute>
            <Leaderboard />
          </ProtectedRoute>
        }
      />
    </Routes>

  );
}

export default App;