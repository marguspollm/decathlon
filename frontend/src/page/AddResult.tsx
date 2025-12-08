import { useEffect, useState } from "react";
import type { Athlete, DecathlonEvent, Score } from "../types/types";

export default function AddResult() {
  const [athletes, setAthletes] = useState<Athlete[]>([]);
  const [events, setEvents] = useState<DecathlonEvent[]>([]);

  const [form, setForm] = useState<Score>({
    result: 0,
    athlete: { id: 0 },
    event: { id: 0 },
  });

  useEffect(() => {
    fetch("http://localhost:8080/events")
      .then((res) => res.json())
      .then((data) => setEvents(data))
      .catch((err) => console.error(err));
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/athletes")
      .then((res) => res.json())
      .then((data) => setAthletes(data))
      .catch((err) => console.error(err));
  }, []);

  const handleResultChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: name === "result" ? Number(value) : value,
    }));
  };

  const handleEventChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setForm((prev) => ({
      ...prev,
      event: { id: Number(e.target.value) },
    }));
  };

  const handleAthleteChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setForm((prev) => ({
      ...prev,
      athlete: { id: Number(e.target.value) },
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await fetch("http://localhost:8080/scores", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      })
      .then((res) => res.json())
      .then(r =>alert(`Added`));
    } catch (err) {
      alert(`Error: ${(err as Error).message}`);
    }
  };

  return (
    <div>
      <h2>Add Event result</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          name="result"
          placeholder="Result"
          value={form.result}
          onChange={handleResultChange}
        />
        <select value={form.athlete.id} onChange={handleAthleteChange}>
          <option value="">-- Select an Athlete --</option>
          {athletes.map((ath) => (
            <option key={ath.id} value={ath.id}>
              {ath.firstName} {ath.lastName}
            </option>
          ))}
        </select>
        <select value={form.event.id} onChange={handleEventChange}>
          <option value="">-- Select an Event --</option>
          {events.map((evt) => (
            <option key={evt.id} value={evt.id}>
              {evt.name}
            </option>
          ))}
        </select>

        <button type="submit">Add</button>
      </form>
    </div>
  );
}
