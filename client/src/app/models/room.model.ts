import { NullTemplateVisitor } from '@angular/compiler';

export class Room {
    id: number | null = null;
    name: string | null = null;
    currentTemperature: number | null = null;
    selectedTemperature: number | null = null;
    powerConsumption: number | null = null;
    radiatorState: boolean | false = false;
}
