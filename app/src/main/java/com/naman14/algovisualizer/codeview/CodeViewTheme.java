package com.naman14.algovisualizer.codeview;

public enum CodeViewTheme {

    AGATE("agate","#030303"),
    ANDROIDSTUDIO("androidstudio","#282b2e"),
    ARDUINO_LIGHT("arduino-light","#FFFFFF"),
    ARTA("arta","#020202"),
    ATELIER_CAVE_DARK("atelier-cave-dark","#19171c"),
    ATELIER_CAVE_LIGHT("atelier-cave-light","#efecf4"),
    ATELIER_DUNE_DARK("atelier-dune-dark","#20201d"),
    ATELIER_DUNE_LIGHT("atelier-dune-light","#fefbec"),
    ATELIER_ESTUARY_DARK("atelier-estuary-dark","#22221b"),
    ATELIER_ESTUARY_LIGHT("atelier-estuary-light","#f4f3ec"),
    ATELIER_FOREST_DARK("atelier-forest-dark","#1b1918"),
    ATELIER_FOREST_LIGHT("atelier-forest-light","#f1efee"),
    ATELIER_HEATH_DARK("atelier-heath-dark","#1b181b"),
    ATELIER_HEATH_LIGHT("atelier-heath-light","#f7f3f7"),
    ATELIER_LAKESIDE_DARK("atelier-lakeside-dark","#161b1d"),
    ATELIER_LAKESIDE_LIGHT("atelier-lakeside-light","#ebf8ff"),
    ATELIER_PLATEAU_DARK("atelier-plateau-dark","#1b1818"),
    ATELIER_PLATEAU_LIGHT("atelier-plateau-light","#f4ecec"),
    ATELIER_SAVANNA_DARK("atelier-savanna-dark","#171c19"),
    ATELIER_SAVANNA_LIGHT("atelier-savanna-light","#ecf4ee"),
    ATELIER_SEASIDE_DARK("atelier-seaside-dark","#131513"),
    ATELIER_SEASIDE_LIGHT("atelier-seaside-light","#f4fbf4"),
    ATELIER_SULPHURPOOL_DARK("atelier-sulphurpool-dark","#202746"),
    ATELIER_SULPHURPOOL_LIGHT("atelier-sulphurpool-light","#f5f7ff"),
    CODEPEN_EMBED("codepen-embed","#020202"),
    COLOR_BREWER("color-brewer","#0f0f0f"),
    DARK("dark","#040404"),
    DARKULA("darkula","#2b2b2b"),
    DEFAULT("default","#F0F0F0"),
    DOCCO("docco","#f8f8ff"),
    DRACULA("dracula","#282a36"),
    FAR("far","#000080"),
    FOUNDATION("foundation","#0e0e0e"),
    GITHUB("github","#f8f8f8"),
    GRAYSCALE("grayscale","#0f0f0f"),
    GRUVBOX_DARK("gruvbox-dark","#282828"),
    GRUVBOX_LIGHT("gruvbox-light","#fbf1c7"),
    HOPSCOTCH("hopscotch","#322931"),
    HYBRID("hybrid","#1d1f21"),
    IDEA("idea","#0f0f0f"),
    IR_BLACK("ir-black","#000000"),
    MONO_BLUE("mono-blue","#eaeef3"),
    MONOKAI_SUBLIME("monokai-sublime","#23241f"),
    MONOKAI("monokai","#272822"),
    OBSIDIAN("obsidian","#282b2e"),
    PARAISO_DARK("paraiso-dark","#2f1e2e"),
    PARAISO_LIGHT("paraiso-light","#e7e9db"),
    POJOAQUE("pojoaque","#073642"),
    PUREBASIC("purebasic","#FFFFDF"),
    QTCREATOR_DARK("qtcreator_dark","#000000"),
    QTCREATOR_LIGHT("qtcreator_light","#ffffff"),
    RAILSCASTS("railscasts","#232323"),
    RAINBOW("rainbow","#474949"),
    SOLARIZED_DARK("solarized-dark","#002b36"),
    SOLARIZED_LIGHT("solarized-light","#fdf6e3"),
    SUNBURST("sunburst","#000000"),
    TOMORROW_NIGHT_BLUE("tomorrow-night-blue","#002451"),
    TOMORROW_NIGHT_EIGHTIES("tomorrow-night-eighties","#2d2d2d"),
    TOMORROW_NIGHT("tomorrow-night","#1d1f21"),
    XCODE("xcode","#0f0f0f"),
    XT256("xt256","#000000"),
    ZENBURN("zenburn","#3f3f3f");

    private String name;
    private String backgroundColor;

    private CodeViewTheme(String name, String backgroundColor){
        this.name=name;
        this.backgroundColor=backgroundColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getName() {
        return name;

    }

    private static CodeViewTheme[]themes;

    public static CodeViewTheme[]listThemes(){
        if(themes==null){
            themes=new CodeViewTheme[]{
                    AGATE,ANDROIDSTUDIO,ARDUINO_LIGHT,ARTA,ATELIER_CAVE_DARK,ATELIER_CAVE_LIGHT,
                    ATELIER_DUNE_DARK,ATELIER_DUNE_LIGHT,ATELIER_ESTUARY_DARK,ATELIER_ESTUARY_LIGHT,
                    ATELIER_FOREST_DARK,ATELIER_FOREST_LIGHT,ATELIER_HEATH_DARK,ATELIER_HEATH_LIGHT,
                    ATELIER_LAKESIDE_DARK,ATELIER_LAKESIDE_LIGHT,ATELIER_PLATEAU_DARK,ATELIER_PLATEAU_LIGHT,
                    ATELIER_SAVANNA_DARK,ATELIER_SAVANNA_LIGHT,ATELIER_SEASIDE_DARK,ATELIER_SEASIDE_LIGHT,
                    ATELIER_SULPHURPOOL_DARK,ATELIER_SULPHURPOOL_LIGHT,CODEPEN_EMBED,COLOR_BREWER,DARK,DARKULA,
                    DEFAULT,DOCCO,DRACULA,FAR,FOUNDATION,GITHUB,GRAYSCALE,GRUVBOX_DARK,GRUVBOX_LIGHT,HOPSCOTCH,
                    HYBRID,IDEA,IR_BLACK,MONO_BLUE,MONOKAI_SUBLIME,MONOKAI,OBSIDIAN,PARAISO_DARK,PARAISO_LIGHT,
                    POJOAQUE,PUREBASIC,QTCREATOR_DARK,QTCREATOR_LIGHT,RAILSCASTS,RAINBOW,SOLARIZED_DARK,
                    SOLARIZED_LIGHT,SUNBURST,TOMORROW_NIGHT_BLUE,TOMORROW_NIGHT_EIGHTIES,TOMORROW_NIGHT,
                    XCODE,XT256,ZENBURN,
            };
        }
        return themes;
    }
}