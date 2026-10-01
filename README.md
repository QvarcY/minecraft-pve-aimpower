# Minecraft PvE AimPower by QvarcY

<!-- AIMPOWER SHOWCASE START -->

## Gameplay

![Minecraft PvE AimPower gameplay](assets/aimpower-gameplay.png)

> Soft aim assistance with explicit hostile-mob target locking and visual target feedback.

### Core features

- PvE-only hostile mob targeting
- players are explicitly excluded from the target pipeline
- soft camera tracking instead of instant snapping
- manual target lock and release
- range and line-of-sight validation
- automatic target release when the target becomes invalid
- glowing locked target
- animated visual target halo
- live target name and distance indicator
- no automatic attacks
- no automatic player movement

### Controls

| Key | Action |
| --- | --- |
| `G` | Toggle PvE AimPower ON / OFF |
| `R` | Lock or release a hostile target |
| `V` | Reserved for AutoToolSwitcher |

<!-- AIMPOWER SHOWCASE END -->

<p align="center">
  <a href="https://github.com/QvarcY/minecraft-pve-aimpower/actions/workflows/build.yml"><img alt="Build" src="https://github.com/QvarcY/minecraft-pve-aimpower/actions/workflows/build.yml/badge.svg"></a>
  <a href="https://github.com/QvarcY/minecraft-pve-aimpower/blob/main/LICENSE"><img alt="License: MIT" src="https://img.shields.io/badge/license-MIT-22c55e"></a>
  <a href="https://buymeacoffee.com/craftin"><img alt="Buy Me a Coffee" src="https://img.shields.io/badge/Buy_Me_a_Coffee-support-FFDD00?logo=buymeacoffee&logoColor=000"></a>
  <a href="https://github.com/sponsors/QvarcY"><img alt="GitHub Sponsors" src="https://img.shields.io/badge/GitHub_Sponsors-support-EA4AAA?logo=githubsponsors&logoColor=fff"></a>
</p>

<p align="center">
  <a href="#latviski">Latviski</a> · <a href="#english">English</a>
</p>

> Smooth client-side PvE aim assistance for Minecraft without player targeting or automatic attacks

**Development target:** Minecraft 26.2 · Fabric Loader 0.19.5+ · Java 25

PvE AimPower is designed to help track and aim at non-player mobs using controlled soft camera movement.

Players are intentionally excluded from the targeting system.

---

## Latviski

### Kas tas ir

Minecraft PvE AimPower ir client-side Fabric mods, kas paredzēts PvE cīņām.

Mērķis ir palīdzēt spēlētājam noturēt tēmēšanu uz izvēlēta moba, izmantojot vienmērīgu un kontrolētu kameras kustību.

Spēlētāji netiek izmantoti kā mērķi.

### Plānotās pamatfunkcijas

- mērķu meklēšana tikai starp non-player dzīvajām entītijām
- hard-coded Player izslēgšana
- target lock
- soft aim bez pēkšņa camera snap
- regulējams FOV
- regulējams maksimālais attālums
- line-of-sight pārbaude
- target atbrīvošana pēc nāves vai aiziešanas ārpus diapazona
- hostile-only režīms
- vēlāk projectile prediction lokam un arbaletam
- vēlāk HUD ar target informāciju

### Ko mods nedara

- netēmē uz spēlētājiem
- automātiski neuzbrūk
- neveic automātisku kustību
- serverī nekas nav jāinstalē

### Uzstādīšana

Pirmais publiskais JAR būs pieejams GitHub Releases pēc v0.1.0 testēšanas.

Būs nepieciešams:

1. Minecraft 26.2
2. Fabric Loader 0.19.5 vai jaunāks
3. Fabric API Minecraft 26.2 versijai
4. PvE AimPower JAR mapē `mods`

Windows noklusētais ceļš parasti ir:

    %AppData%\.minecraft\mods

Ja launcher izmanto atsevišķas instances, izmanto konkrētās instances `mods` mapi.

### Izstrādes plāns

`v0.1` — target engine + soft aim
`v0.2` — konfigurācija + HUD
`v0.3` — projectile lead prediction
`v1.0` — stabila publiskā relīze

### Atbalsti projektu

- **Buy Me a Coffee:** https://buymeacoffee.com/craftin
- **GitHub Sponsors:** https://github.com/sponsors/QvarcY
- **QvarcY GitHub:** https://github.com/QvarcY

---

## English

### What is it

Minecraft PvE AimPower is a client-side Fabric mod built for PvE combat.

Its goal is to help the player keep aim on a selected mob using smooth and controlled camera movement.

Players are not valid targets.

### Planned core features

- targeting only non-player living entities
- hard-coded Player exclusion
- target lock
- smooth aim without instant camera snapping
- configurable FOV
- configurable maximum range
- line-of-sight validation
- automatic target release when dead or out of range
- hostile-only mode
- later projectile prediction for bows and crossbows
- later target HUD

### What it does not do

- does not target players
- does not attack automatically
- does not move the player automatically
- requires nothing to be installed on the server

### Installation

The first public JAR will be available through GitHub Releases after v0.1.0 testing.

Requirements:

1. Minecraft 26.2
2. Fabric Loader 0.19.5 or newer
3. Fabric API for Minecraft 26.2
4. PvE AimPower JAR in the `mods` folder

The default Windows location is usually:

    %AppData%\.minecraft\mods

If your launcher uses separate instances, use that instance's own `mods` folder.

### Roadmap

`v0.1` — target engine + soft aim
`v0.2` — configuration + HUD
`v0.3` — projectile lead prediction
`v1.0` — stable public release

### Support the project

- **Buy Me a Coffee:** https://buymeacoffee.com/craftin
- **GitHub Sponsors:** https://github.com/sponsors/QvarcY
- **QvarcY on GitHub:** https://github.com/QvarcY

---

## License

MIT License

Created by [QvarcY](https://github.com/QvarcY)

Minecraft PvE AimPower is an independent community project and is not affiliated with Mojang or Microsoft.
