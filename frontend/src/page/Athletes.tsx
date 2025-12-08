import { useEffect, useState } from "react";
import type { Athlete } from "../types/types";

export default function Athletes() {
  const [athletes, setAthleteList] = useState<Athlete[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/athletes")
      .then((res) => res.json())
      .then(setAthleteList);
  }, []);

  const handleClick = (id: number) => {
    fetch(`http://localhost:8080/scores/athlete/${id}`)
      .then((res) => res.json())
      .then(r =>alert(`${r.athlete.firstName} ${r.athlete.lastName} : ${r.points}`));
  };

  return (
    <div>
      <h2>Athletes</h2>
        <ul>
          {athletes.map((ath, idx) => (
            <li key={idx} onClick={() => handleClick(ath.id!)}>
              <strong>{ath.firstName} {ath.lastName}</strong>
            </li>
          ))}
        </ul>
    </div>
  );
}
