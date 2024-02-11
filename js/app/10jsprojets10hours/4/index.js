var notes = [{ id : 1,
    text : "ciaone"
},
{
    id : 2,
    text: `<h1>test</h1><div><span>joji</span> <b>jklj</b> <img src="https://jprime.ismcdn.jp/mwimgs/2/b/-/img_2bea8aec30f7856ad3e475933fd13af7996132.jpg" alt="monkei majik"/>`
},
{
    id : 3,
    text: `Lorem ipsum dolor sit amet consectetur adipisicing elit. Id, provident fugit ea vel odio vitae voluptas amet a assumenda iusto adipisci voluptatum corporis. Facilis reiciendis magnam eligendi qui perferendis sunt.
    Sint, neque. Minima itaque consequatur veniam maxime distinctio at! Dolor at fugit cupiditate nostrum reprehenderit tempora pariatur dolore, nobis itaque eligendi alias explicabo perferendis vitae amet, quas quasi perspiciatis id?
    Debitis aspernatur cupiditate cumque voluptatibus odio culpa modi tempora alias. Ut natus sit quisquam ipsum maiores earum quasi fugit cupiditate, odio adipisci illo mollitia quos perferendis molestias, id molestiae quibusdam.
    Explicabo, quidem enim consectetur veniam eum cupiditate similique porro aut odit sapiente deleniti. Est eaque, culpa error delectus iste mollitia itaque omnis sunt asperiores, vitae molestias odit in facere enim?
    Ducimus deserunt quos aperiam error molestias! Quia deserunt qui, explicabo excepturi perspiciatis enim quaerat ipsum impedit maiores corporis voluptatibus veritatis eaque voluptatem provident, nihil, dicta labore? Nisi ea repellendus incidunt!
    Molestiae ratione nam perferendis incidunt, laboriosam natus. Voluptatibus tempore, quas beatae in reprehenderit quos, voluptate sint perspiciatis dolores cumque quae molestias fugiat, obcaecati illo optio? Debitis quam nam laboriosam voluptas.
    Ratione vero impedit praesentium sequi illum alias dicta sapiente incidunt atque similique qui repudiandae quas neque repellendus corrupti animi asperiores optio odio temporibus corporis, consectetur nulla omnis voluptates blanditiis? Corporis?
    Recusandae fugiat nostrum corrupti nemo consequuntur debitis sit exercitationem esse odio provident quidem eligendi molestiae alias maxime sequi, ducimus consectetur labore fuga. Animi aliquam incidunt et inventore est unde aut.
    Voluptas placeat possimus hic repudiandae, eaque quaerat, aut, laudantium odio numquam voluptatum cupiditate corrupti ipsa dolores quis at eos pariatur cumque? Error adipisci placeat ea voluptatum aut, voluptates sed soluta.
    Accusantium voluptatem doloribus aliquam accusamus sapiente fugiat, maxime earum. Reiciendis adipisci sequi facilis aut ex asperiores nemo deserunt architecto nostrum labore vero, omnis numquam. Dolorem eum repudiandae illum sint perspiciatis?`
}];


const container = document.getElementById("container");
showNotes();

function showNotes(){
    container.innerHTML = "";
    notes.forEach(element => {
        displayNote(element, false);
    });
}

function displayNote(note, isToggleOn){
        var noteContainer = `
            <div class="note-container" id="note-${note.id}">
            <div class="note-header">`;

        if(isToggleOn)
            noteContainer+= `<button class="editBtn toggledOn" id="editBtn-${note.id}" onclick="toggleEditNote(${note.id})">`;
        else
            noteContainer+= `<button class="editBtn" id="editBtn-${note.id}" onclick="toggleEditNote(${note.id})">`;

        noteContainer+=`   
            <i class="fa-solid fa-pen-to-square"></i>
            </button>
            <button class="deleteBtn" id="deleteBtn-${note.id}" onclick="deleteNote(${note.id})">
                <i class="fa-solid fa-trash"></i>
            </button>
            </div>`;

            if(isToggleOn)
                noteContainer+= `<div class="note-content">
            <textarea id="textArea-${note.id}" name="textArea" rows="18" cols="35"></textarea>
            </div>`;
            else
                noteContainer+= `<div class="note-content">${note.text}</div>`;
            noteContainer+=`</div>`;
        container.innerHTML += noteContainer;
}

function toggleEditNote(id){
    const btn = document.getElementById("editBtn-"+id);
    const noteContent = document.querySelector(`#note-${id} .note-content`);
    if(btn.classList.contains("toggledOn")){
        btn.classList.remove("toggledOn");
        noteContent.innerHTML = toggleUneditable(id);
    } else {
        btn.classList.add("toggledOn");
        noteContent.innerHTML = toggleEditable(id);
    }
}

function toggleEditable(id){
    const currNote = notes.filter( note => {
        return note.id == id;
    });
    return `<textarea id="textArea-${currNote[0].id}" name="textArea" rows="18" cols="35">${currNote[0].text}</textarea>'`;
}

function toggleUneditable(id){
    const noteContent = document.getElementById("textArea-"+id);
    notes = notes.map( note => {
        if (note.id === id) {
          return {...note, text: noteContent.value};
        }
        return note;
      });
    
    var parser = new DOMParser();
    var doc = parser.parseFromString(noteContent.value, 'text/html');
    console.log(doc.body);
    return doc.body.innerHTML;
}

function deleteNote(id){
    notes = notes.filter(function(note) {
        return  note.id !== id;
    });
    showNotes();
}

document.getElementById("addBtn").addEventListener("click", addNote);
function addNote(){
    const nextId = getNextId();
    notes.push({id : nextId, text : ""});
    displayNote(notes[notes.length-1], true);
}

function getNextId(){
    if(notes.length > 0)
        return notes[notes.length-1].id + 1;
    else return 1;
}