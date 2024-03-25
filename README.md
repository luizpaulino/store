## Setup e uso da aplicação

- Requisitos:
    - Docker
    - Gradle

- Clonar o projeto e rodar o script store.sh -> Esse script garante a ordem necessária de inicialização.
- Collection do [arquivo JSON da coleção](./store_collection.json).
    - Utilizar a collection para facilitar as requisições para a aplicação, a collection pode ser importada no Insomnia ou no Postman.

# Ordem de Inicialização do Projeto

Ao iniciar o ambiente de desenvolvimento local que utiliza contêineres Docker para cada serviço do projeto, é crucial seguir uma ordem específica de inicialização dos serviços para garantir que a infraestrutura esteja configurada corretamente. No contexto deste projeto, é necessário iniciar o projeto "Items" primeiro, pois ele é responsável por criar a rede de conexão Docker que será utilizada pelos demais serviços.

## Por que iniciar o projeto "Items" primeiro?

O projeto "Items" desempenha um papel fundamental na infraestrutura do sistema, pois é responsável por criar a rede de conexão Docker que será compartilhada pelos outros serviços, como o carrinho de compras ("Cart"), autenticação ("Auth"), e pagamento ("Payment"). Ao iniciar o projeto "Items" primeiro, garantimos que a rede Docker esteja pronta e configurada para aceitar conexões dos demais serviços.

## Procedimento Recomendado

Segue abaixo a ordem recomendada para iniciar os serviços do projeto:

1. **Items**: Inicie o serviço "Items" para criar a rede de conexão Docker.
2. **Cart**, **Auth**, **Payment**: Após o serviço "Items".

# Requisições com Header "userId" Obrigatório

Neste projeto, é obrigatório incluir o header "userId" em determinadas requisições, especificamente nas operações relacionadas à adição de itens no carrinho ("Cart") e no processo de pagamento ("Payment"). O header "userId" é essencial para identificar o usuário associado à ação realizada, garantindo segurança e consistência nas operações.

## Header "userId" nas Requisições

Ao realizar as seguintes operações, certifique-se de incluir o header "userId" com o ID do usuário correspondente:

### Adição de Item no Carrinho ("Cart")

Ao enviar uma requisição para adicionar um item ao carrinho, certifique-se de incluir o header "userId" com o ID do usuário.

# Autenticação de Serviços

## Visão Geral

Este documento descreve o comportamento do serviço de autenticação (Auth) em relação à criação automática de usuários para outros sistemas. Quando o serviço de autenticação é iniciado, verifica-se se os usuários necessários para os sistemas de itens (Items), carrinho (Cart) e pagamento (Payment) existem no banco de dados. Se algum desses usuários estiver ausente, o serviço de autenticação os criará automaticamente.

## Processo de Verificação e Criação Automática de Usuários

O serviço de autenticação executa o seguinte processo durante a inicialização:

1. Verificação da Existência de Usuários:
   - O serviço de autenticação verifica se os usuários para os sistemas de itens, carrinho e pagamento estão presentes no banco de dados.

2. Criação Automática de Usuários Ausentes:
   - Se algum dos usuários necessários estiver ausente, o serviço de autenticação os criará automaticamente com as credenciais padrão.

## Credenciais Padrão dos Usuários

Os usuários criados automaticamente terão as seguintes credenciais padrão:

- **Usuário de Itens (Items)**
  - Email: items@service.com
  - Senha: itemsService

- **Usuário de Carrinho (Cart)**
  - Email: cart@service.com
  - Senha: cartService

- **Usuário de Pagamento (Payment)**
  - Email: payment@service.com
  - Senha: paymentService

Os usuários ficam configurados na seguinte estrutura, dentro do application.yml:

    authentication-service:
      user:
        username: payment@service.com
        password: paymentService
## Nota

Este processo de criação automática de usuários ocorre apenas durante a inicialização do serviço de autenticação e é projetado para garantir a integridade e o funcionamento adequado dos sistemas inter-relacionados.

# Sistema de Carrinho - Inspiração AliExpress

## Visão Geral

O sistema de carrinho neste aplicativo foi projetado com base na experiência de usuário oferecida pelo AliExpress, um dos principais marketplaces de comércio eletrônico. A inspiração do AliExpress é refletida na abordagem de manter o carrinho preenchido com itens selecionados, garantindo uma experiência de compra eficiente e conveniente para os usuários.

## Funcionalidades Principais

### Persistência do Carrinho

- **Manutenção do Carrinho Preenchido**: O sistema de carrinho mantém os itens selecionados pelo usuário mesmo após o fechamento do aplicativo ou a conclusão de uma sessão de navegação. Isso permite que os usuários retornem ao carrinho a qualquer momento para revisar ou concluir a compra.

- **Sincronização do Carrinho com o Usuário**: O carrinho está associado ao usuário, garantindo que ele tenha acesso aos mesmos itens em diferentes dispositivos ou sessões de login. Isso proporciona consistência e conveniência durante a experiência de compra.

### Experiência de Compra Fluida

- **Adição Rápida de Itens ao Carrinho**: Os usuários podem adicionar itens ao carrinho com facilidade e rapidez, sem interrupções significativas na navegação pelo aplicativo.

- **Visualização e Edição do Carrinho**: O sistema oferece uma interface intuitiva para que os usuários visualizem e editem os itens no carrinho, incluindo a modificação de quantidades, remoção de itens e atualização de opções de compra.

### Notificações e Lembretes

- **Lembretes de Carrinho Abandonado**: Quando um usuário deixa o aplicativo com itens no carrinho sem concluir a compra, o sistema pode enviar notificações ou lembretes para incentivá-lo a retornar e finalizar a compra.

- **Ofertas Especiais e Descontos**: O sistema pode apresentar ofertas especiais ou descontos exclusivos para itens no carrinho, incentivando os usuários a concluir a compra.

## Nota

O sistema de carrinho inspirado no AliExpress visa proporcionar uma experiência de compra agradável e eficiente para os usuários, promovendo a retenção e a fidelidade dos clientes por meio de práticas de design centradas no usuário e funcionalidades intuitivas.