# PluginUltra

Minecraft 26.2 (Paper 26.2) / Java 25 向けの Minecraft サーバープラグインです。

## プロジェクト構成

```text
plugin-ultra/
├── .github/
│   └── workflows/
│       └── release.yml                       # GitHub Actions 自動リリース設定 (Java 25)
├── pom.xml                                   # Mavenビルド定義 (Paper-API 26.2.build.126-stable / Java 25)
├── .gitignore                                # Git除外ファイル設定
├── README.md                                 # 本ドキュメント
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── example/
        │           └── pluginultra/
        │               ├── PluginUltra.java  # プラグインのメインクラス
        │               └── command/
        │                   └── UltraCommand.java # /ultra テストコマンド実装
        └── resources/
            ├── paper-plugin.yml              # Paper用プラグイン定義 (Modern形式, 26.2)
            └── plugin.yml                    # 互換用プラグイン定義 (commands/permissions定義)
```

---

## 実装されている機能（テスト用コマンド）

ゲーム内で `/ultra` またはチャット補完（Tab キー）を使って動作確認ができます。

| コマンド | 説明 |
| :--- | :--- |
| `/ultra` または `/ultra help` | 利用可能なサブコマンドの一覧を表示します。 |
| `/ultra ping` | プラグインの動作確認。`Pong!` と返信し、心地よい効果音（PLING）が鳴ります。 |
| `/ultra heal` | 実行したプレイヤーの**体力・満腹度を全快**にし、レベルアップ効果音が鳴ります。 |

---

## 自動ビルド＆リリース (GitHub Actions)

本リポジトリを GitHub に push し、`main` ブランチへアップロード（マージ）すると、**GitHub Actions が自動的に起動**します。

1. クラウドランナー上で **Java 25** 環境がセットアップされます。
2. Maven により `mvn clean package` が実行され、JAR ファイルがビルドされます。
3. ビルド完了後、リポジトリの **Releases** ページに `v1.0.0-build.X` タグとともに新しいリリースが作成され、ビルドされた JAR ファイルが自動添付されます。

> [!TIP]
> **GitHub リポジトリ設定の確認**:
> GitHub のリポジトリ設定画面（**Settings** > **Actions** > **General** > **Workflow permissions**）で **"Read and write permissions"** が選択されていることを確認してください。

---

## サーバーへの導入方法

1. GitHub の **Releases** からダウンロードした `plugin-ultra-*.jar` を用意します。
2. Paper 26.2 サーバーの `plugins/` フォルダ内に配置します。
3. サーバーを起動（または `/reload confirm`）します。
4. ゲーム内で `/ultra ping` を入力してテストします。
