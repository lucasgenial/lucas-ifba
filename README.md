# lucas-ifba
Projetos Criados durante o curso de licenciatura em computação

O diretor está à procura de uma solução para se comunicar por email com os professores de diversas turmas
de diversos cursos, sendo que muitas vezes deseja-se aplicar alguns critérios. Ex.: apenas os professores dos
terceiros e quartos anos do integrado; todos os professores que lecionam no curso de TI (integrado e
subsequente), apenas os professores do 4° semestre lic. em computação; todos os calouros do superior
(licenciaturas em computação, intercultural indígena e química), etc.

Inicialmente seus colegas de trabalho sugeriram criar grupos de contato na conta de email e/ou lista de
distribuição de email (googlegroups da vida), etc. Contudo, dada a grande mudança de professores por
turmas, fruto de afastamentos, remoções/redistribuições, chegada de novos professores, etc., esta solução
não vem dando muito certo e sempre tem professor que não está recebendo ou ex-professor recebendo os
emails indevidamente.
Batendo um papo com um professor de TI sobre os dias de hoje, onde temos muita dificuldade em gerenciar
tantas informações, visto o grande volume e a rapidez com que elas mudam, o diretor comentou que sempre
que há uma mudança de professor de turma/disciplina na instituição ele recebe um email automático
contendo um arquivo (.txt) atualizado, com informações de todos os professores e suas respectivas turmas,
mas ainda assim não consegue estabelecer uma forma eficiente de mandar seus emails quando se faz
necessário selecionar turmas específicas (exemplos anteriores). Comentou até que da última vez que
precisava dar um aviso importante para os professores de curso de licenciatura em computação demorou
1h50min para selecionar corretamente, visto que era para enviar apenas estes professores. No bate-papo, em
resposta o professor de TI falou: mas este problema é mais simples do que parece. Os alunos do quarto
semestre conseguem resolver! Surpreso, o diretor solicitou então que o professor viabilizasse esta solução
por meio da turma.
Então, este professor de TI que conversou com o diretor, pediu ao professor de POO II, visto que é a
disciplina mais próxima do problema, que encaminhasse essa solução junto aos seus alunos. Já que é
assim....Vamos lá!

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

Devem ser considerados os comentários incluídos nas classes e interfaces disponibilizadas. Alguns são
informativos outros são instruções a serem cumpridas. Uma vez entendido o código disponibilizado, vamos
resolver o problema do diretor!
Partindo das classes e interfaces disponibilizadas desenvolva uma solução que permita o usuário informar o
caminho onde se encontra o arquivo .txt (contendo dados de todos os professores no padrão predefinido).
Este arquivo será disponibilizado - input.txt. A partir da identificação do padrão deste arquivo será possível
fazer o método stringRegistroParaObjeto (setar atributos do objeto) e o toStringEscritaArquivo (retornar
uma String no padrão esperado) para registros de entrada (filtráveis).
Este arquivo .txt tem os emails institucionais/oficiais dos professores fornecidos pelo setor de recursos
humanos, por exemplo: douglasteodoro@ifba.edu.br. Porém, pode ser que o diretor queira cadastrar emails
extraoficiais, como um email alternativo. Ex: douglasteodoro@gmail.com. Assim, espera-se que seja
possível adicionar e remover registros extraoficiais, mas sem alterar o arquivo .txt que contem os dados
oficiais. Um exemplo onde se deseja remover um registro seria o caso de um professor deixar de pertencer
ao campus. Neste caso a remoção do email oficial já seria feita pelo setor de recursos humanos, mas o
extraoficial teria que ser removido a partir da sua aplicação pelo usuário.
Perceba então que há duas fontes (arquivos) de informações: o arquivo oficial (.txt) e outro arquivo com os
registros extras. Estes arquivos extras devem ser mantidos em um arquivo serializado, com nome fixo
(sugestão: input.ser). As inclusões e exclusões extraoficiais só ocorrem nele. O arquivo .txt é “somente
leitura”.
Entendidas as duas fontes de informações, vamos entender como se espera que a aplicação funcione. O
usuário deve ter disponível, a qualquer momento, as seguintes opções:

Entendidas as duas fontes de informações, vamos entender como se espera que a aplicação funcione. O
usuário deve ter disponível, a qualquer momento, as seguintes opções:
- Escolher arquivo .txt contendo dados oficiais
- Carregar dados extraoficiais (que podem ou não existir)
- Inserir novo registro extraoficial
- Remover registro extraoficial
- Definir critérios (cursos, modalidades e períodos)
- Gerar dados de saída
- Sair do programa

- Escolher arquivo .txt contendo dados oficiais: permite o usuário informar o caminho onde se encontra o
arquivo com os registros. Caso neste arquivo exista(m) linha(s) fora do padrão esperado, deve ser lançada a
exceção PadraoRegistroEntradaInvalidoException, do tipo checada. Considerando que a qualquer momento
o usuário pode optar por carregar registros de um arquivo .txt, se faz necessário, no momento de sua leitura,
evitar que seja adicionado o mesmo professor (registro) mais de uma vez, evitando duplicidade (desperdício
de memória, processador, etc.). Por exemplo, se formos nesta opção por 2 vezes e informarmos o mesmo
arquivo, a primeira vez todos os registros serão considerados. Já na segunda, nenhum deveria ser.

- Carregar dados extraoficiais (que podem ou não existir): nesta opção deve ser lido o arquivo serializado
contendo informações extraoficiais, que também farão parte dos registros existentes filtragem. Novamente, é
necessário evitar duplicidade.

- Inserir novo registro extraoficial: ler e adicionar aos registros existentes um novo registro extraoficial.
Novamente, é necessário evitar duplicidade.

- Remover registro extraoficial: remover um registro extraoficial. Esta remoção deve considerar apenas
registros extraoficiais e nunca remover registros existentes a partir do .txt.

- Definir critérios (cursos, modalidades e períodos): permite o usuário definir/informar os critérios para
filtragem dos registros. Deseja-se ter critérios para curso, modalidade e período.

- Gerar dados de saída: neste momento deve ser criado o filtro (objeto), adicionar a este filtro os critérios e
obter os filtrados resultantes dos critérios, considerando os dados de entrada existentes. Os registros
resultantes do filtro devem ser gravados em um arquivo de saída. Com relação ao arquivo de saída, ele tanto
poderá ter nome e caminho fixo (no código java), como também isso ser definido pelo usuário, como ocorre
no caso do arquivo de entrada. Você decide! De qualquer forma será disponibilizado um exemplo de arquivo
de saída – output.txt, pois a partir dele é possível fazer o método stringRegistroParaObjeto e
toStringEscritaArquivo para registros de saída, bastando observar e entender padrão predefinido. Além
disso, deseja-se que o usuário possa escolher se o arquivo de saída estará ordenado pela identificação ou
pelo email.

- Sair do programa: neste momento deve-se atualizar o arquivo serializado, uma vez que é possível durante
a execução da aplicação terem ocorridas inclusões e remoções e fechar o programa.

Sua aplicação pode ser modo texto, modo gráfico com JOptionPane ou com o pacote Swing. Seja qual for
sua escolha deseja-se este menu disponível.
