import { useState } from "react";
import type { Athlete } from "../types/types";

export default function AddAthlete() {
  const [form, setForm] = useState<Athlete>({
    firstName: "",
    lastName: "",
    country: "",
    age: 0,
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: name === "age" ? Number(value) : value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await fetch("http://localhost:8080/athletes", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });
      setForm({ firstName: "", lastName: "", country: "", age: 0 });
    } catch (err) {
      alert(`Error: ${(err as Error).message}`);
    }
  };

  return (
    <div>
      <h2>Add User</h2>
      <form onSubmit={handleSubmit}>
        <label>First name:</label>
        <input
          type="text"
          name="firstName"
          placeholder="First Name"
          value={form.firstName}
          onChange={handleChange}
        />
        <label>Last name:</label>
        <input
          type="text"
          name="lastName"
          placeholder="Last name"
          value={form.lastName}
          onChange={handleChange}
        />
        <label>Country:</label>
        <input
          type="text"
          name="country"
          placeholder="Country"
          value={form.country}
          onChange={handleChange}
        />
        <label>Age:</label>
        <input
          type="number"
          name="age"
          placeholder="Age"
          value={form.age}
          onChange={handleChange}
        />
        <button type="submit">Add</button>
      </form>
    </div>
  );
}
