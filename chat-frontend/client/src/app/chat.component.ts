import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface Message {
  role: 'user' | 'assistant';
  content: string;
}

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {
  messages: Message[] = [];
  userInput = '';
  loading = false;

  constructor(private http: HttpClient) {}

  sendMessage() {
    if (!this.userInput.trim()) return;
    const userMsg: Message = { role: 'user', content: this.userInput };
    this.messages.push(userMsg);
    const prompt = this.userInput;
    this.userInput = '';
    this.loading = true;
    this.http.post<{response: string}>(
      'http://localhost:8000/chat',
      { prompt, agent: 'chat' }, // Add agent field
      { headers: { 'Content-Type': 'application/json' } }
    ).subscribe({
      next: (res) => {
        this.messages.push({ role: 'assistant', content: res.response });
        this.loading = false;
      },
      error: () => {
        this.messages.push({ role: 'assistant', content: 'Error: Could not reach backend.' });
        this.loading = false;
      }
    });
  }

  clearChat() {
    this.messages = [];
  }
}
