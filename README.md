## WeCuiFix (Fabric Mod)

A fabric mod that fix WorldEdit work with WorldEditCUI on 1.20.5+

Please install this mod with both WorldEdit and WorldEditCUI

----------

### Update for the newer WorldEditCUI

From [this](https://github.com/EngineHub/WorldEditCUI/commit/63ec72e101e172167a5b6a7b5a91a53450dd1f46) commit, WECUI has **Explicitly mark an incompatibility** with WorldEdit. In order to fix that, you have to modify WECUI's config file manually to bypass the fabric's compatibility check with this followed step:

#### Options 1. Modify WECUI Jar:

1. Open WECUI Jar from what ever unzip software you want.

![image](https://github.com/user-attachments/assets/ca96d5b2-fb30-4de4-a83d-606fab6bf07f)

2. Open fabric.mod.json, remove all the "breaks" part, and save it.

![image](https://github.com/user-attachments/assets/30189b96-2c6c-4e7e-8e67-b09ce145c6eb)

#### Option 2. Add dependency override file in minecraft config:

1. Open `.minecraft/config` (If you enabled version isolation, open `.minecraft/version/%YOUR_VERSION%/config` instead.

![image](https://github.com/user-attachments/assets/6c6db5e7-b438-4885-a38d-c8f275d86fad)

3. In this folder, create a new file named `fabric_loader_dependencies.json` and input:
```
{
  "version": 1,
  "overrides": {
    "worldeditcui": {
      "-breaks": {
        "worldedit": "IGNORED"
      }
    }
  }
}
```

3. Save the file. (Or you can download the file from [here](https://github.com/user-attachments/files/20972440/fabric_loader_dependencies.zip))


#### After finish those step, you could be able to use both of the mod together.

----------

### License

Copyright (C) 2025, Lumine1909;

This program is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hospe that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
