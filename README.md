# ProReactive_Movie
Ejercicio1 ProReactive: Gestor de Salas de Cine

# Entidades
## Ciudad
## Sala
## Pelicula
## Cartelera

*Crear película:*
con auto_increment
`{
"titulo": "Interestelar",
"original": "Interstellar",
"descripcion": "Gracias a un descubrimiento, un grupo de científicos y exploradores, encabezados por Cooper, se embarcan en un viaje espacial para encontrar un lugar con las condiciones necesarias para reemplazar a la Tierra y comenzar una nueva vida allí",
"reparto": "Matthew McConaughey, Anne Hathaway, Jessica Chastain",
"director": "Christopher Nolan",
"duracion": "169 minutos",
"estreno": "26 de octubre de 2014",
"clasificacion": "PG13, +12",
"genero": "Ciencia ficción, Drama, Distopia"
}`

`{
"titulo": "Matrix",
"original": "What is the Matrix",
"descripcion": "La película plantea que en el futuro, tras una dura guerra, casi todos los seres humanos han sido esclavizados por las máquinas y las inteligencias artificiales creadas. Estas los tienen en suspensión y con sus mentes conectadas a una realidad virtual llamada -Matrix- que representa el final del siglo xx. Los seres humanos son usados por las máquinas para obtener energía y los pocos que no están suspendidos, o que han sido liberados, viven en la ciudad Zion y tienen naves que se mueven por el subsuelo, entrando de forma clandestina a la Matrix para liberar otros conectados.",
"reparto": "Keanu Reeves, Carrie-Ann Moss, Laurence Fishburne, Hugo Weaving",
"director": "Hermanas Wachowski",
"duracion": "136 minutos",
"estreno": "31 de marzo de 1999",
"clasificacion": "R, +18",
"genero": "Acción, Ciencia ficción"
}`

`{
"titulo": "El Hombre de la Tierra",
"original": "The Man from Earth",
"descripcion": "La película cuenta la historia de John Oldman, un profesor de universidad que asegura tener 14 000 años de edad y que ha sobrevivido hasta nuestros días. Se basa únicamente en la conversación de los personajes para mantener la trama, sin efectos especiales ni escenas de acción. Se trata de un discurso intelectual entre un supuesto hombre de las cavernas y sus actuales colegas de trabajo, en su despedida como docente universitario. En las horas que transcurren, las reacciones de sus amigos científicos son muy variadas y van surgiendo preguntas, cuestiones, revelaciones y reacciones de todo tipo.",
"reparto": "David Lee Smith, Tony Todd, John Billingsley, Ellen Crawford, Annika Peterson, William Katt, ",
"director": "Richard Schenkman",
"duracion": "87 minutos",
"estreno": "2007",
"clasificacion": "Todos",
"genero": "Drama, Filosofía, Ciencia, Religión"
}`

`{
"titulo": "Predestination",
"original": "Predestination",
"descripcion": "Un individuo viaja atrás en el tiempo para capturar al famoso criminal conocido como «Terrorista Fallido» (Fizzle Bomber).",
"reparto": "Ethan Hawke, Sarah Snook, Noah Taylor",
"director": "Michael Spierig, Peter Spierig",
"duracion": "97 minutos",
"estreno": "2014",
"clasificacion": "R",
"genero": "Ciencia ficción, intersex, distópico, time travel"
}`

*Crear Cartelera:*
`{
"horario": "6PM - 9PM",
"sala": "Ultra",
"pelicula": "Predestination"
}`

*Crear Sala:*
`{
"nombre": "iMax",
"ciudad": "Barranquilla",
"formato": "iMax",
"nivel": "VIP",
"confiteria": "SI"
}`

`{
"nombre": "2D",
"ciudad": "Barranquilla",
"formato": "2D, 2D Plus, 2D Ultra",
"nivel": "Estándar, Plus, Ultra",
"confiteria": "SI"
}`

*Crear Ciudad:*
`{
"nombre": "Barranquilla"
}`
`{
"nombre": "Medellín"
}`
`{
"nombre": "Bogotá"
}`
`{
"nombre": "Cartagena"
}`

# Postman
## Crear Película:

Método: POST

URL: http://localhost:6090/peliculas/save

Cuerpo (en formato JSON):
`{
"titulo": "Interestelar",
"original": "Interstellar",
"descripcion": "Gracias a un descubrimiento, un grupo de científicos y exploradores, encabezados por Cooper, se embarcan en un viaje espacial para encontrar un lugar con las condiciones necesarias para reemplazar a la Tierra y comenzar una nueva vida allí",
"reparto": "Matthew McConaughey, Anne Hathaway, Jessica Chastain",
"director": "Christopher Nolan",
"duracion": "169 minutos",
"estreno": "26 de octubre de 2014",
"clasificacion": "PG13, +12",
"genero": "Ciencia ficción, Drama, Distopia"
}`

Respuesta esperada: La película creada en formato JSON.

## Consultar Todas las Películas:

Método: GET

URL: http://localhost:6090/peliculas/all

Respuesta esperada: Lista de películas en formato JSON.

## Consultar una Película por ID:

Método: GET

URL: http://localhost:6090/peliculas/one/{id}

Reemplaza {id} con el ID real de una película existente.

Respuesta esperada: Película con el ID especificado en formato JSON.

## Consultar Película por Título:

Método: GET

URL: http://localhost:6090/peliculas/onet/{titulo}

Reemplaza {titulo} con el título real de una película existente.

Respuesta esperada: Lista de películas con el título especificado en formato JSON.

## Eliminar Película por ID:

Método: GET

URL: http://localhost:6090/peliculas/delete/{id}

Reemplaza {id} con el ID real de una película existente.

Respuesta esperada: Mensaje indicando que la película ha sido eliminada.

## Actualizar Película por ID:

Método: PUT

URL: http://localhost:6090/peliculas/update/{id}

Reemplaza {id} con el ID real de una película existente.

Cuerpo (en formato JSON):
`{
"titulo": "Predestination",
"original": "Predestination",
"descripcion": "Un individuo viaja atrás en el tiempo para capturar al famoso criminal conocido como «Terrorista Fallido» (Fizzle Bomber).",
"reparto": "Ethan Hawke, Sarah Snook, Noah Taylor",
"director": "Michael Spierig, Peter Spierig",
"duracion": "97 minutos",
"estreno": "2014",
"clasificacion": "R",
"genero": "Ciencia ficción, intersex, distópico, time travel"
}`

Respuesta esperada: Película actualizada en formato JSON.
