# RTF Support in the extension

The 11.3.0 release of the extension includes the new ability to copy the currently selected text in [Rich Text Format](http://en.wikipedia.org/wiki/Rich_Text_Format) (RTF).  It allows the font name & size to be used for the RTF output to be specified independently via the preferences panel:

![http://dl.dropbox.com/u/191565/jdev/copy-prefs.png](http://dl.dropbox.com/u/191565/jdev/copy-prefs.png)

This functionality has been tested and confirmed to work successfully in:

  * [WordPad](http://en.wikipedia.org/wiki/WordPad) on Microsoft Windows, and
  * [TextEdit](http://en.wikipedia.org/wiki/TextEdit) on Mac OS X

It specifies the following surrounding RTF markup:

```
    {\rtf1\ansi
    {\fonttbl{\f0\fnil <<font-name-specified-in-preferences>>;}}
    {\colortbl;<<color table entries>>;}
    {\pard\f0\fsN

    <<markup-for-each-styled-fragment>>

    \par}}
```

Each [StyledFragment](http://download.oracle.com/docs/cd/E14571_01/apirefs.1111/e13403/oracle/javatools/editor/language/StyledFragment.html) within the active selection is specified with the following markup:

```
    {\b\i\cfN <escaped-text>}
```

where:

  * \b is specified if the fragment is to be bold
  * \i is specified if the fragment is to be italic
  * \cfN specifies the entry in the colortbl to use