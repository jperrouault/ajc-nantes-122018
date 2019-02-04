import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'prixCategory'
})
export class PrixCategoryPipe implements PipeTransform {
    transform(prix: number, type?: string): string {
        if (type === "string") {
            if (prix < 0) { return "Négatif"; }
            if (prix > 0) { return "Positif"; }
            return "Neutre";
        }

        if (prix < 0) { return "red"; }
        if (prix > 0) { return "green"; }
        return "blue";
    }
}
