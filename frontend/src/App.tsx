import { useState } from "react";
import "./App.css";
import AddAthlete from "./page/AddAthlete";
import Athletes from "./page/Athletes";
import AddResult from "./page/AddResult";

function App() {
  const [page, setPage] = useState<"addAthlete" | "addResult" | "athletes">("athletes");

  return (
    <div>
      <nav>
        <button onClick={() => setPage("addAthlete")}>Add Athlete</button>
        <button onClick={() => setPage("addResult")}>Add Result</button>
        <button onClick={() => setPage("athletes")}>Athletes</button>
      </nav>

      {page === "athletes" && <Athletes />}
      {page === "addAthlete" && <AddAthlete />}
      {page === "addResult" && <AddResult/>}
    </div>
  );
}

export default App;
