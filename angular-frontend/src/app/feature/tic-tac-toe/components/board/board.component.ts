import { Component } from '@angular/core';
import { SquareComponent } from "../square/square.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-board',
  imports: [SquareComponent, CommonModule],
  templateUrl: './board.component.html',
  styleUrl: './board.component.scss'
})
export class BoardComponent {

  squares!: any[];  // Declare an array for the squares
  xIsNext!: boolean;  // Boolean flag to track whether it's X's turn or O's
  winner!: string | null;  // Track the winner, null if no winner yet

  constructor() {
    this.newGame();  // Initialize the game
  }

  newGame() {
    this.squares = Array(9).fill(null);  // Initialize squares to an array of 9 null values
    this.xIsNext = true;  // X starts the game
    this.winner = null;  // No winner initially
  }

  get player() {
    return this.xIsNext ? 'X' : 'O';  // Return the current player (X or O)
  }

  makeMove(index: number) {
    if (!this.squares[index] && !this.winner) {  // Only allow a move if the square is empty and there's no winner
      this.squares.splice(index, 1, this.player);  // Update the square with the current player's symbol
      this.xIsNext = !this.xIsNext;  // Switch turns
    }
    this.winner = this.calculateWinner();  // Check if there's a winner
  }

  calculateWinner() {
    const lines = [
      [0, 1, 2],
      [3, 4, 5],
      [6, 7, 8],
      [0, 3, 6],
      [1, 4, 7],
      [2, 5, 8],
      [0, 4, 8],
      [2, 4, 6]
    ];

    // Check each line for a winner
    for (let i = 0; i < lines.length; i++) {
      const [a, b, c] = lines[i];
      if (this.squares[a] && this.squares[a] === this.squares[b] && this.squares[a] === this.squares[c]) {
        return this.squares[a];  // Return the winner symbol (X or O)
      }
    }
    return null;  // Return null if no winner yet
  }

}
