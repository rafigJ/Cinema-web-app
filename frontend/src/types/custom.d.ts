declare module '*.svg' {
    const content: string;
    export default content;
}

declare module '*.png';

declare module '*.module.css' {
    const classes: { [key: string]: string };
    export default classes;
}