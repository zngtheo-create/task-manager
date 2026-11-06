import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task, TaskRequest } from './model/task.model';


@Injectable({ providedIn: 'root' })
export class TaskService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:9090/api/tasks';


  getAll(): Observable<Task[]> {
    return this.http.get<Task[]>(this.apiUrl);
  }

  getById(id: number): Observable<Task> {
    return this.http.get<Task>(`${this.apiUrl}/${id}`);
  }

  create(task: TaskRequest): Observable<Task> {
    return this.http.post<Task>(this.apiUrl, task);
  }

  update(id: number, task: TaskRequest): Observable<Task> {
    return this.http.put<Task>(`${this.apiUrl}/${id}`, task);
  }

  toggle(id: number): Observable<Task> {
    return this.http.patch<Task>(`${this.apiUrl}/${id}/toggle`, {});
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
