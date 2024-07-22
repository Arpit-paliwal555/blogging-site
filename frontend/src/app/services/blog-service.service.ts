import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Date } from 'mongoose';
import { Observable } from 'rxjs';

interface Blog{
  id: Number;
  title: String;
  content: String;
  userId:Number,
  date: Date
}
interface User{
  id: Number;
  username: String;
  email: String;
}
@Injectable({
  providedIn: 'root'
})

export class BlogServiceService {

  public url = 'http://localhost:8080/api/blogs';
  public urlForUsers = 'http://localhost:8080/api/users';
  constructor(private http: HttpClient) { }

  getBlogs():Observable<Blog[]> {
    return this.http.get<Blog[]>(this.url);
  }
  getBlogById(id:Number):Observable<Blog> {
    return this.http.get<Blog>(this.url + '/' + id);
  }

  saveBlog(blog:any):Observable<Blog> {
    return this.http.post<Blog>(this.url, blog);
  }
  getUserById(id:Number):Observable<User> {
    return this.http.get<User>(this.urlForUsers +'/' + id);
  }
}
