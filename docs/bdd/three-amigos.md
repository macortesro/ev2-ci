# Three Amigos – Login

**Roles**
- **PO/BA**: asegura valor de negocio (acceso rápido y seguro).
- **Dev**: factibilidad técnica (endpoint /login, validaciones).
- **QA**: criterios verificables, casos límite.

**Historia de Usuario**
Como usuario registrado, quiero iniciar sesión con email y contraseña para acceder a mi cuenta.

**Criterios de Aceptación**
- CA1: Credenciales válidas -> acceso al dashboard. 
- CA2: Credenciales inválidas -> mensaje de error y acceso denegado.
- CA3: Email con formato válido (usuario@dominio).
- CA4: Registrar intentos fallidos (para posterior auditoría).

**Ejemplos**
| Caso | Email                  | Password     | Resultado                     |
|------|------------------------|--------------|-------------------------------|
| OK   | user@empresa.com       | Correcta123  | Dashboard + acceso exitoso    |
| Pass | user@empresa.com       | Incorrecta123| Error: Credenciales inválidas |
| Form | userempresa.com        | Correcta123  | Error: Formato de email inválido |
| NoReg| desconocido@empresa.com| Cualquier123 | Error: Usuario no registrado  |
