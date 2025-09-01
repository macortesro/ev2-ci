import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 10,               // usuarios virtuales
  duration: '30s',       // prueba corta (smoke)
  thresholds: {
    http_req_failed: ['rate<0.01'],      // <1% errores
    http_req_duration: ['p(95)<500'],    // p95 < 500ms
  },
};

export default function () {
  // Endpoint de demo de k6 (reemplaza por tu API cuando exista)
  const url = 'https://test.k6.io/'; // o tu /login real
  const res = http.get(url);
  check(res, {
    'status es 200': (r) => r.status === 200,
  });
  sleep(1);
}
