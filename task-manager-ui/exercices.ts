// 1. Types de base
const prenom: string = 'Aminata';
let age: number = 20;
const actif: boolean = true;

// 2. Tableaux
const nombres: number[] = [1, 2, 3, 4, 5];
const doubles = nombres.map(n => n * 2);
const pairs = nombres.filter(n => n % 2 === 0);
console.log(doubles, pairs);

// 3. Fonction fléchée avec types
const additionner = (a: number, b: number): number => a + b;
console.log(additionner(2, 3));

// 4. Interface et objet
interface Personne {
  nom: string;
  age: number;
  email?: string;
}
const p: Personne = { nom: 'Ouedraogo', age: 25 };
console.log(p);

// 5. Classe
class Compteur {
  private valeur = 0;
  incrementer(): void { this.valeur++; }
  getValeur(): number { return this.valeur; }
}
const c = new Compteur();
c.incrementer();
c.incrementer();
console.log(c.getValeur());

// 6. async / await
const attendre = (ms: number) => new Promise(resolve => setTimeout(resolve, ms));
async function demo() {
  console.log('Début');
  await attendre(1000);
  console.log('Fin après 1 seconde');
}
demo();