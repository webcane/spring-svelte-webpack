import SvelteApp from './App.svelte';

const app = new SvelteApp({
    target: document.querySelector('.ssw'),
    props: {
        name: 'world'
    }
});

export default app;