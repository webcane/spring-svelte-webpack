<script>
    export let name;

    async function fetchItems() {
        const response = await fetch('/items');

        if (response.ok) {
            return response.json();

        } else {
            throw new Error("failed to load items");
        }
    }
</script>

<style>
    h1 {
        color: purple;
    }
</style>

<h1>Hello {name}!</h1>

<div class="items">
    {#await fetchItems()}
        <p>...waiting</p>
    {:then items}
        {#if items}
            <ul>
                {#each Array.from(items) as item }
                    <li>{item.title}</li>
                {/each}
            </ul>
        {:else}
            <p>No items available yet</p>
        {/if}
    {:catch error}
        <p style="color: red">{error.message}</p>
    {/await}
</div>

<p>
    <a href="/login" title="Login with GitHub">Sign in</a>
</p>
