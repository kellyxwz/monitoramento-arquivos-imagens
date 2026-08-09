# 📸 Directory Watcher API

Serviço automatizado desenvolvido em **Java** e **Spring Boot** para monitoramento contínuo de diretórios locais do sistema operacional em tempo real. 

O sistema escuta a criação de arquivos em uma pasta configurada: se o arquivo for uma imagem válida (`.jpg`, `.jpeg`, `.png`), seus metadados (nome, tamanho e data de criação) são extraídos de forma otimizada e salvos no banco de dados **MySQL** via **JPA**. Arquivos em formatos não permitidos são automaticamente removidos do disco.

---

## 🚀 Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3.x**
  * *Spring Data JPA* (Persistência e ORM)
  * *Spring Web* (Endpoints REST)
  * *Spring Async* (Execução em segundo plano)
* **MySQL** (Banco de dados relacional)
* **Java NIO (`java.nio.file`)** (Manipulação de arquivos e `WatchService`)
* **Jackson** (Serialização e formatação de datas)
* **Maven** (Gerenciamento de dependências)

---

## 🏗️ Arquitetura e Fluxo de Execução

O projeto segue o padrão de **Arquitetura em Camadas (Layered Architecture)** para garantir o desacoplamento de responsabilidades:

```text
[ Sistema Operacional ]
         │ (Criação de Arquivo / ENTRY_CREATE)
         ▼
[ DirectoryWatcherService ] (@Async + WatchService)
         │
         ├── ❌ Arquivo Inválido (.txt, .pdf, etc.) ──► Deleta do Disco (Files.deleteIfExists)
         │
         └── 🟢 Imagem Válida (.jpg, .jpeg, .png)
             │  1. Aguarda 500ms (Evita File Lock durante a escrita)
             │  2. Extrai Metadados do SO (BasicFileAttributes)
             │  3. Instancia ImageResponseDTO
             │
             ▼
      [ ImageService ]
             │
             ▼
     [ ImageRepository ] ──► [ Banco MySQL (tb_images) ]
