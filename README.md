# PluginUltra

Minecraft 26.2 (Paper 26.2) / Java 25 向けの最小構成 Minecraft サーバープラグインです。

## プロジェクト構成

```text
plugin-ultra/
├── .github/
│   └── workflows/
│       └── release.yml                       # GitHub Actions 自動リリース設定 (Java 25)
├── pom.xml                                   # Mavenビルド定義 (Paper-API 26.2 / Java 25)
├── .gitignore                                # Git除外ファイル設定
├── README.md                                 # 本ドキュメント
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── example/
        │           └── pluginultra/
        │               └── PluginUltra.java  # プラグインのメインクラス
        └── resources/
            ├── paper-plugin.yml              # Paper用プラグイン定義 (Modern形式, 26.2)
            └── plugin.yml                    # 互換用プラグイン定義
```

---

## 自動ビルド＆リリース (GitHub Actions)

本リポジトリを GitHub に push し、`main` ブランチへアップロード（マージ）すると、**GitHub Actions が自動的に起動**します。

1. クラウドランナー上で **Java 25** 環境がセットアップされます。
2. Maven により `mvn clean package` が実行され、JAR ファイルがビルドされます。
3. ビルド完了後、リポジトリの **Releases** ページに `v1.0.0-build.X` タグとともに新しいリリースが作成され、ビルドされた JAR ファイルが自動添付されます。

> [!TIP]
> **GitHub リポジトリ設定の確認**:
> GitHub のリポジトリ設定画面（**Settings** > **Actions** > **General** > **Workflow permissions**）で **"Read and write permissions"** が選択されていることを確認してください（Release の作成と JAR のアップロードに必要です）。

---

## ローカルでのビルド（任意）

### 必要な環境
- **Java 25 (JDK 25)**
- **Maven** (3.8.0 以上) または IntelliJ IDEA / VS Code などの IDE

### ビルドコマンド
```bash
mvn clean package
```
ビルドが完了すると、`target/` ディレクトリ内に `plugin-ultra-1.0.0-SNAPSHOT.jar` が生成されます。

---

## サーバーへの導入方法

1. GitHub の **Releases** からダウンロード（またはローカルビルド）した `plugin-ultra-*.jar` を用意します。
2. Paper 26.2 サーバーのルートにある `plugins/` フォルダ内に配置します。
3. サーバーを起動します。
4. コンソールに以下のログが表示されれば、正常に読み込まれています:
   ```text
   [INFO] [PluginUltra] PluginUltra has been enabled!
   ```
