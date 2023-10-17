import Dexie from 'dexie';
import 'dexie-observable';

const db = new Dexie('User');//'$$uuid'
db.version(1).stores({ Models2: '++id',waitingModels:'++id' });


db.version(2).stores({});
export default db;  