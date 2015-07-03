
foreach(string fileName in Directory.GetFiles("."))
{
	if(fileName.EndsWith("java"))
	{
		Console.WriteLine(string.Format("{0}=$(Outpackage)/{0}.class", Path.GetFileNameWithoutExtension(fileName)));
	}
}

Console.WriteLine();
Console.Write("all: ");

foreach(string fileName in Directory.GetFiles("."))
{
	if(fileName.EndsWith("java"))
	{
		Console.Write(string.Format("$({0}) ", Path.GetFileNameWithoutExtension(fileName)));
	}
}

Console.WriteLine();

foreach(string fileName in Directory.GetFiles("."))
{
	if(fileName.EndsWith("java"))
	{
		Console.WriteLine(string.Format("$({0}): {1}", Path.GetFileNameWithoutExtension(fileName),Path.GetFileName(fileName)));
		Console.WriteLine(string.Format("\tjavac -cp \"$(Output)\" -d \"$(Output)\" {0}", Path.GetFileName(fileName)));
		Console.WriteLine();
	}
}
