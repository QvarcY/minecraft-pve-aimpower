# Changelog

All notable changes to Minecraft PvE AimPower are documented here.

## [Unreleased]

## [0.1.0] - 2026-10-01

### Added

- Minecraft 26.2 client-side Fabric mod foundation
- Fabric Loader 0.19.5+ and Java 25 support
- `G` hotkey for enabling and disabling AimPower
- `R` hotkey for hostile target lock and release
- hostile mob scanner with a 12-block maximum range
- target selection up to 45 degrees from the current look direction
- line-of-sight target validation
- hard-coded Player exclusion in the target scanner
- secondary Player exclusion inside the aim controller
- automatic target release when the target becomes invalid
- smooth camera tracking for locked hostile mobs
- capped yaw and pitch movement per tick
- torso-biased aim point
- glowing locked-target feedback
- animated dual particle target markers
- flame halo for stronger target visibility
- actionbar target name and live distance
- GitHub Actions build workflow
- English and Latvian documentation
- project funding and support links

### Safety boundaries

- no player targeting
- no automatic attacks
- no automatic player movement
- no server-side component required