import createClient from 'openapi-fetch'

import { paths } from './apiV1/schema'

const API_BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL

// openapi-fetch 기반의 client
export const client = createClient<paths>({
  baseUrl: API_BASE_URL,
  credentials: 'include',
})

// fetch 기반의 apiFetch
export const apiFetch = (url: string, options?: RequestInit) => {
  options = options || {}
  options.credentials = 'include'

  if (options?.body) {
    const headers = new Headers(options.headers || {})

    // 이미 호출하는 쪽에서 Content-Type을 지정했다면 덮어쓰지 않고 그대로 사용
    if (!headers.has('Content-Type')) {
      headers.set('Content-Type', 'application/json')
    }

    options.headers = headers
  }

  return fetch(`${API_BASE_URL}${url}`, options).then((res) => res.json())
}
