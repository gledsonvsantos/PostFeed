
# **PostFeed**

**PostFeed** é um aplicativo Android desenvolvido para consumir posts de uma API pública, exibindo-os em uma interface responsiva. Ele utiliza **Clean Architecture**, **MVVM**, e está integrado com Firebase para monitoramento de erros e coleta de dados analíticos.

---

## **📱 Funcionalidades**
- Exibe posts vindos da API pública [JSONPlaceholder](https://jsonplaceholder.typicode.com/posts).
- Permite rastrear cliques nos posts com **Firebase Analytics**.
- Detecta e reporta erros com **Firebase Crashlytics**.
- Estrutura modular baseada em **Clean Architecture**:
    - Camadas: **UI**, **Domínio**, **Dados**.
- Interface responsiva utilizando **RecyclerView**.

---

## **🛠️ Tecnologias Utilizadas**
### **Principais**
- **Kotlin**: Linguagem de programação.
- **Jetpack Components**:
    - **ViewModel** e **LiveData** para gerenciar estados.
    - **Navigation Component** para navegação.
    - **RecyclerView** para exibição de listas.
- **Hilt**: Injeção de dependência.
- **Retrofit**: Consumo de APIs REST.
- **Firebase**:
    - **Crashlytics**: Relatórios de erros.
    - **Analytics**: Coleta de eventos.

### **Outras**
- **Coroutines**: Manipulação de operações assíncronas.
- **Gson**: Conversão de JSON.
- **DiffUtil**: Atualizações eficientes no RecyclerView.

---

## **📂 Estrutura do Projeto**
O projeto segue a arquitetura **MVVM com Clean Architecture**, dividido em três camadas:

```
com.example.postfeed
├── data                 // Camada de dados (APIs, DTOs, Repositórios)
├── domain               // Camada de domínio (Use Cases, Modelos)
├── ui                   // Camada de apresentação (Fragments, Adapters)
│   ├── adapters         // Adapters do RecyclerView
│   ├── listeners        // Interfaces para interação com UI
│   ├── view             // Fragments e telas
│   └── viewmodel        // ViewModels
└── di                   // Configuração de dependências (Hilt)
```

---

## **🚀 Como Rodar o Projeto**

### **Pré-requisitos**
1. **Android Studio** instalado.
2. Configuração do Firebase:
    - Baixe o arquivo `google-services.json` do [Firebase Console](https://console.firebase.google.com/).
    - Coloque o arquivo na pasta `app/`.

### **Passos**
1. Clone o repositório:
   ```bash
   git clone https://github.com/gledsonvsantos/postfeed.git
   cd postfeed
   ```
2. Abra o projeto no Android Studio.
3. Sincronize as dependências no Gradle.
4. Conecte um dispositivo ou inicie um emulador.
5. Rode o app:
    - **Run > Run 'app'** ou pressione `Shift + F10`.

---

## **🧪 Testes**

O projeto inclui testes unitários e de interface.

### **Rodar Testes Unitários**
Execute os testes unitários:
```bash
./gradlew test
```

### **Rodar Testes Instrumentados**
Execute os testes de interface:
```bash
./gradlew connectedAndroidTest
```

---

## **📖 API Consumida**
O aplicativo utiliza a API pública do [JSONPlaceholder](https://jsonplaceholder.typicode.com/), especificamente o endpoint de posts:

- **Endpoint**: `https://jsonplaceholder.typicode.com/posts`

---

## **🔗 Recursos Implementados**
- [x] Consumo de API com Retrofit.
- [x] Gerenciamento de dependências com Hilt.
- [x] Relatórios de erros com Firebase Crashlytics.
- [x] Coleta de eventos com Firebase Analytics.
- [x] Atualizações dinâmicas no RecyclerView com DiffUtil.
- [x] Testes unitários e instrumentados.
