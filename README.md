# URL-Shortener-Service
## 1. Introduction
A URL shortener. This service allows users to shorten long URLs, redirect to original URLs, track clicks, set expiry times, and enforce rate limiting to prevent abuse.

---

## 2. Technologies
<ul>
  <li>Spring Boot</li>
  <li>Spring Data JPA (Hibernate & PostgreSQL)</li>
  <li>Spring Scheduler</li>
  <li>Bucket4j – Rate limiting</li>
  <li>Lombok</li>
</ul>

---

## 3. Endpoints
### 1. Shorten URL

**POST:** /api/shorten

**Rate Limited:** Yes (e.g., 1 request/min)

**Description:** Generates a shortened URL.

**Request Body**
```json
{
  "longUrl": "https://example.com/very/long/url",
  "expiresAt": "2025-04-30T23:59:59"
}
```

**longUrl (required):** The original URL to be shortened.

**expiresAt (optional):** The expiration datetime in ISO format.

**Request Body**
<ul>
  <li>200 OK - Seccessfully created </li><br>
  
  ```json
{
 "http://localhost:8080/abc123"
}
```

<li>429 TOO MANY REQUESTS – Rate limit exceeded. </li> <br>

 ```json
"Rate limit exceeded. Try again later."

```

</ul>

### 2. Redirect to Original URL

**GET:** /{shortCode}

**Description:** Redirects to the original long URL.
  
### **Path Variable**
  <ul>
    <li>shortCode: The 6-character shortened code.</li>
  </ul>

### **Responses**
<ul>
  <li>302 FOUND – Redirects to the long URL.</li>
  <li>404 NOT FOUND – URL not found.</li>
  <li>410 GONE – URL has expired.</li>
</ul>






















