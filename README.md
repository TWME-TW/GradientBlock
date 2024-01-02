 # Gradient Block

An architect's tool that can generate a gradient-colored block arrangement using two blocks.

## Command
 - `/gradientblock [complexity]` (`/gb`/`/gblock`) Open gradient block gui (default complexity: 73)
 - `/gradientblock reload` (`/gb reload`/`/gblock reload`) Reload config

## Usage
Placing two blocks in the GUI will generate a gradient block arrangement; then, clicking on the center block will automatically place that arrangement in your hand.

## Permission
 - `gradientblock.use`
 - `gradientblock.reload`

## depend
 - FastAsyncWorldEdit

## Config
```yaml
commands:
  no-permission: "§6Gradient Block §7>> §cYou do not have permission to use this command."
  need-player: "§6Gradient Block §7>> §cYou must be a player to use this command."
  complexity-need-integer: "§6Gradient Block §7>> §cThe complexity must be an integer."
  complexity-range: "§6Gradient Block §7>> §cThe complexity must be between 1 and 100."
  reload: "§6Gradient Block §7>> §aConfig reloaded."

gui:
  title: "§6Gradient Block"
  item-name: ""
  item-type: "BLACK_STAINED_GLASS"
```

## Image
![Screenshot](https://i.imgur.com/mS8o1j0.png)
![Screenshot](https://i.imgur.com/7urrpCM.png)