# Release checklist

## Build

- [ ] Java 25 build passes
- [ ] GitHub Actions passes
- [ ] release JAR is generated
- [ ] sources JAR is not presented as the normal user download

## PvE validation

- [ ] players can never become targets
- [ ] mob target lock works
- [ ] soft aim works without camera snapping
- [ ] line-of-sight filtering works
- [ ] dead targets are released
- [ ] out-of-range targets are released
- [ ] multiplayer client test passes

## Documentation

- [ ] README matches current behavior
- [ ] installation instructions are current
- [ ] changelog is updated
- [ ] release notes identify the supported Minecraft version
