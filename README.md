# lucas-ifba
Projetos Criados durante o curso de licenciatura em computação

O diretor está à procura de uma solução para se comunicar por email com os professores de diversas turmas
de diversos cursos, sendo que muitas vezes deseja-se aplicar alguns critérios. Ex.: apenas os professores dos
terceiros e quartos anos do integrado; todos os professores que lecionam no curso de TI (integrado e
subsequente), apenas os professores do 4° semestre lic. em computação; todos os calouros do superior
(licenciaturas em computação, intercultural indígena e química), etc.

Considere as interfaces disponibilizadas:

- Filtrado: define o comportamento esperado para objetos que queiram ser referenciados como resultados
de aplicação de um filtro em um objeto Filtrável.
- Filtravel: define o comportamento esperado para objetos que queiram ser referenciados como um
Filtrável. Observe que todo Filtrável deve ter todos os comportamentos de um Filtrado, uma vez que
submetida uma entrada para um filtro (filtrável) deseja-se obter uma saída (filtrado). Isso ficará mais
claro à medida que entender as interfaces e classes disponibilizadas. Isso é garantido pela interface
ItemFiltro e sua relação com as interfaces Filtravel e Filtrado.
- ItemFiltro: Garante comportamentos comuns entre filtráveis e filtrados, permitindo a partir de um
filtrável se obter um filtrado.
- Criterio: define o comportamento esperado para critérios (ex.: turma, curso, ano/semestre, etc.)
- Registro: define o comportamento esperado para registros. Têm-se registros de entrada e registros de
saída, cujo formato deve ser predefinido e disponibilizado.
Inicialmente foi identificado um Filtravel modelado na classe (incompleta) a seguir:
- Professor: modela a entidade professor, que por sua vez tem Cursos, Modalidades (classes também
disponibilizadas).

Inicialmente foi identificado um filtrado modelado na classe (incompleta) a seguir:

- ResultadoFiltro: classe concreta que implementa a interface Filtrado, pois modela o resultado de um
filtro
Inicialmente foram identificados três critérios, modelados nas classes (incompletas) a seguir:
- CriterioCursos: armazena o critério a ser atendido para curso. Por exemplo, o filtrável é válido se
tiver valor do curso “TB” ou “TI”.
- CriterioModalidades: armazena o critério a ser atendido para modalidades. Por exemplo, o filtrável
é válido se tiver valor da modalidade “Integrada” ou “Subsequente”.
- CriterioPeriodos: armazena o critério a ser atendido para períodos. Por exemplo, o filtrável é válido
se tiver valor de período 2 3 ou 4
Inicialmente foram identificados dois registros, modelados nas classes (incompletas) a seguir:
- Professor: Já mencionada e modela também os registros (linhas) contidos em um arquivo de entrada
- ResultadoFiltro: Já mencionada e modela também os registros (linhas) contidos em um arquivo de
saída

Entendidas as duas fontes de informações, vamos entender como se espera que a aplicação funcione. O
usuário deve ter disponível, a qualquer momento, as seguintes opções:
 Escolher arquivo .txt contendo dados oficiais
 Carregar dados extraoficiais (que podem ou não existir)
 Inserir novo registro extraoficial
 Remover registro extraoficial
 Definir critérios (cursos, modalidades e períodos)
 Gerar dados de saída
 Sair do programa
