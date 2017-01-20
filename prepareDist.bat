XCOPY /s "E:\Users\anbal\Documents\NetBeansProjects\EORA Updater\dist\Everything needed but main jar" "E:\Users\anbal\Documents\NetBeansProjects\EORA\dist"
MKDIR "E:\Users\anbal\Documents\NetBeansProjects\EORA\dist\UpdateCache"
DEL "E:\Users\anbal\Documents\NetBeansProjects\EORA\dist\README.txt"
powershell.exe -nologo -noprofile -command "& { Add-Type -A 'System.IO.Compression.FileSystem'; [IO.Compression.ZipFile]::CreateFromDirectory('E:\Users\anbal\Documents\NetBeansProjects\EORA\dist', 'Ratting_Advisor_0-0-0.zip'); }"
