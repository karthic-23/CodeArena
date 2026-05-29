import { API_URL } from "../config";

export const apiFetch = async (
  endpoint,
  options = {}
) => {
  const token = localStorage.getItem("token");

  const headers = {
    ...(options.headers || {})
  };

  if (token) {
    headers.Authorization = `Bearer ${token}`;
  }

  return fetch(`${API_URL}${endpoint}`, {
    ...options,
    headers
  });
};