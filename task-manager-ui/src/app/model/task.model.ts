export interface task {

    id : number
    titre : String;
    description? : String;
    complete : boolean;
    createdAt : String;
}

export interface TaskRequest{

    titre : String;
    description? : String;

}
