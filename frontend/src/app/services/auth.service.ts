import { HttpStatusCode, HttpHeaders  } from '@angular/common/http';
import { Token } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { response } from 'express';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authUrl = 'http://localhost:8080/api/users/authenticate';
  private token: String|null = null;
  constructor(private http: HttpClient) { }

  authorize(username: string, password: string): Observable<string> {
    const credentials = { username, password };
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'text/plain' // Ensure the server sends the response as plain text
    });
    console.log("token: " + this.token);
    return this.http.post(this.authUrl, credentials, { headers, responseType: 'text' }).pipe(
      tap(token => {this.token = token; console.log('Token:', this.token)})
      );
  }

  getToken(): String | null {
    return this.token;
  }
}
