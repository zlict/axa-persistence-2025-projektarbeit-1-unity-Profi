const URL = 'http://localhost:8080';
const FORM_MODES = { NEW: 'new', EDIT: 'edit' };
let formMode = FORM_MODES.NEW;
let claimHandlerForm;
let claimHandlers = [];

const saveClaimHandler = (e) => {
  e.preventDefault();
  const formData = new FormData(e.target);
  const claimHandler = {
    employeeNumber: formData.get('employeeNumber'),
    firstName: formData.get('firstName'),
    lastName: formData.get('lastName'),
    department: formData.get('department')
  };
  if (formMode === FORM_MODES.NEW) {
    fetch(`${URL}/claim-handlers`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(claimHandler)
    }).then(result => result.json()).then(newHandler => {
      claimHandlers.push(newHandler);
      renderClaimHandlers();
    });
  } else if (formMode === FORM_MODES.EDIT) {
    fetch(`${URL}/claim-handlers/${formData.get('claimHandlerId')}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(claimHandler)
    }).then(result => result.json()).then(updatedHandler => {
      claimHandlers = claimHandlers.map(h => h.claimHandlerId == updatedHandler.claimHandlerId ? updatedHandler : h);
      renderClaimHandlers();
    });
  }
  formMode = FORM_MODES.NEW;
  e.target.reset();
};

const deleteClaimHandler = (id) => {
  fetch(`${URL}/claim-handlers/${id}`, { method: 'DELETE' }).then(() => {
    claimHandlers = claimHandlers.filter(h => h.claimHandlerId != id);
    renderClaimHandlers();
  });
};

const editClaimHandler = (id) => {
  formMode = FORM_MODES.EDIT;
  const handler = claimHandlers.find(h => h.claimHandlerId == id);
  claimHandlerForm.querySelector('input[name=claimHandlerId]').value = handler.claimHandlerId;
  claimHandlerForm.querySelector('input[name=employeeNumber]').value = handler.employeeNumber;
  claimHandlerForm.querySelector('input[name=firstName]').value = handler.firstName;
  claimHandlerForm.querySelector('input[name=lastName]').value = handler.lastName;
  claimHandlerForm.querySelector('input[name=department]').value = handler.department;
};

const indexClaimHandlers = () => {
  fetch(`${URL}/claim-handlers`).then(r => r.json()).then(result => {
    claimHandlers = result;
    renderClaimHandlers();
  });
  renderClaimHandlers();
};

const createCell = (text) => {
  const cell = document.createElement('td');
  cell.innerText = text;
  return cell;
};

const createActionsCell = (id) => {
  const cell = document.createElement('td');
  const deleteButton = document.createElement('button');
  deleteButton.innerText = "Delete";
  deleteButton.addEventListener('click', () => deleteClaimHandler(id));
  cell.appendChild(deleteButton);
  const editButton = document.createElement('button');
  editButton.innerText = "Edit";
  editButton.addEventListener('click', () => editClaimHandler(id));
  cell.appendChild(editButton);
  return cell;
};

const renderClaimHandlers = () => {
  const display = document.querySelector('#claimHandlerDisplay');
  display.innerHTML = '';
  claimHandlers.forEach(handler => {
    const row = document.createElement('tr');
    row.appendChild(createCell(handler.claimHandlerId));
    row.appendChild(createCell(handler.employeeNumber));
    row.appendChild(createCell(handler.firstName));
    row.appendChild(createCell(handler.lastName));
    row.appendChild(createCell(handler.department));
    row.appendChild(createActionsCell(handler.claimHandlerId));
    display.appendChild(row);
  });
};

document.addEventListener('DOMContentLoaded', function () {
  claimHandlerForm = document.querySelector('#claimHandlerForm');
  claimHandlerForm.addEventListener('submit', saveClaimHandler);
  indexClaimHandlers();
});
