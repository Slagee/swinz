export class Room {
    name: string | null = null;
    currentTemperature: number | null = null;
    selectedTemperature: number | null = null;
    powerConsumption: number | null = null;
    radiatorState: boolean | null = null;
    radiatorForcedDown: boolean | null = null;
    lightWeeklyConsumption: number | null = null;
    monthlyLight: any[] | null = null;
    monthlyPowerConsumption: any[] | null = null;
    monthlyRadiatorTime: any[] | null = null;
    id: number | null = null;
}
