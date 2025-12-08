export interface Athlete {
    id?: number;
    firstName: string;
    lastName: string;
    country: string;
    age: number;
    scores?: [];

}
export interface AthleteScore {
    athlete: Athlete;
    points: number;
}

export interface Score{
    result: number,
    athlete: {
        id: number
    },
    event: {
        id: number
    }
}

export interface DecathlonEvent {
    id?: number,
    name: string,
    isTrack: boolean;
    parameterA: number,
    parameterB: number,
    parameterC: number
}