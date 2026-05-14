# Publishing To Maven Central

This project is configured for Sonatype Central Portal publishing.

## Prerequisites

Before publishing, make sure all of the following are ready:

- Sonatype Central Portal account
- verified namespace for `io.github.zhangpengpaul`
- GPG key available locally
- GPG public key uploaded to a public keyserver
- Central Portal user token

Official references:

- Central Portal Maven publishing:
  - https://central.sonatype.org/publish/publish-portal-maven/
- Publishing requirements:
  - https://central.sonatype.org/publish/requirements/
- GPG requirements:
  - https://central.sonatype.org/publish/requirements/gpg/

## GPG Key

Current signing identity:

- `Peng Zhang <zhang.peng.paul@gmail.com>`
- `Key ID: 2391C0B3A368C4BF`
- `Fingerprint: 6FD28B5E0BF8FD54B413AFCB2391C0B3A368C4BF`

## Settings.xml

Copy the example file:

- `docs/maven-settings-central.example.xml`

into your Maven user settings, or merge the `servers` section into:

- `~/.m2/settings.xml`

The `central` server credentials must come from the Sonatype Central Portal
token page.

## What Gets Published

This repository contains multiple modules, but only the main SDK is intended
for Central consumption:

- `convoai-sdk`

The examples module is configured with:

- `maven.deploy.skip=true`

so the example artifacts do not get uploaded.

## Publishing Command

Use:

```bash
mvn clean deploy -DskipTests
```

With the current plugin configuration:

- artifacts are signed with GPG during `verify`
- sources and javadocs are attached
- the bundle is uploaded to Central Portal
- `autoPublish=false`, so the first release still requires manual confirmation
  in the Central Portal UI

## First Release Recommendation

For the first public release, use a non-snapshot version such as:

- `0.1.0`

Before running `deploy`, update:

- root `pom.xml` version
- `convoai-sdk` inherited version
- README dependency snippet

## Verification Checklist

Before publishing:

- `mvn -q test`
- `mvn -q package -DskipTests`
- confirm README dependency version matches the release version
- confirm `LICENSE` is present
- confirm `sources`, `javadocs`, and signatures are generated during build
